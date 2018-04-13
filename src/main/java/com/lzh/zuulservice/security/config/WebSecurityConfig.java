package com.lzh.zuulservice.security.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lzh.zuulservice.security.entrypoint.RestAuthenticationEntryPoint;
import com.lzh.zuulservice.security.extractor.TokenExtractor;
import com.lzh.zuulservice.security.filter.CustomCorsFilter;
import com.lzh.zuulservice.security.filter.JwtTokenAuthenticationProcessingFilter;
import com.lzh.zuulservice.security.model.SkipPathRequestMatcher;
import com.lzh.zuulservice.security.provider.JwtAuthenticationProvider;

/**
 * Created by 
 * <p>
 * 
 * <p>
 * Describe:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String AUTHENTICATION_HEADER_NAME = "Authorization";
    public static final String AUTHENTICATION_URL = "/auth/auth/login";
    public static final String REFRESH_TOKEN_URL = "/auth/token";
    public static final String API_ROOT_URL = "/**";
	
    @Autowired private RestAuthenticationEntryPoint authenticationEntryPoint;
//    @Autowired private AuthenticationSuccessHandler successHandler;
    @Autowired private AuthenticationFailureHandler failureHandler;
    @Autowired private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired private TokenExtractor tokenExtractor;

    @Autowired private AuthenticationManager authenticationManager;

//    @Autowired private ObjectMapper objectMapper;
    
    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter(List<String> pathsToSkip, String pattern) throws Exception {
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, pattern);
        JwtTokenAuthenticationProcessingFilter filter
            = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }
    
    // 装载BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	List<String> permitAllEndpointList = Arrays.asList(
                AUTHENTICATION_URL,
                REFRESH_TOKEN_URL
            );
    	
    	
        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                //认证异常入口
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)
                
                // 基于token，所以不需要session
                .and()
                	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers(permitAllEndpointList.toArray(new String[permitAllEndpointList.size()]))
                    .permitAll()
                .and()
                	.authorizeRequests()
	                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	
	                // 允许对于网站静态资源的无授权访问
	                .antMatchers(
	                        HttpMethod.GET,
	                        "/",
	                        "/*.html",
	                        "/favicon.ico",
	                        "/**/*.html",
	                        "/**/*.css",
	                        "/**/*.js"
	                ).permitAll()
//                // 所有 /login 的POST请求 都放行
//                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                // 对于获取token的rest api要允许匿名访问
//                .antMatchers("/auth/**").permitAll()
//                //允许 druid
//                .antMatchers("/druid/**").permitAll()
//                // 除上面外的所有请求全部需要鉴权认证
//                .anyRequest().authenticated().and()
                .and()
	                .authorizeRequests()
	                .antMatchers(API_ROOT_URL).authenticated() // Protected API End-points
                .and()
	                .addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class)
	                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(permitAllEndpointList,
	                API_ROOT_URL), UsernamePasswordAuthenticationFilter.class);
        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }
}
