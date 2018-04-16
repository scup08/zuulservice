package com.lzh.zuulservice.security.provider;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.lzh.common.util.JwtTokenUtil;
import com.lzh.zuulservice.security.model.JwtAuthenticationToken;
import com.lzh.zuulservice.security.model.JwtToken;
import com.lzh.zuulservice.security.model.RawAccessJwtToken;
import com.lzh.zuulservice.security.model.UserContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * An {@link AuthenticationProvider} implementation that will use provided
 * instance of {@link JwtToken} to perform authentication.
 * 
 * @author vladimir.stankovic
 *
 * Aug 5, 2016
 */
@Component
@SuppressWarnings("unchecked")
public class JwtAuthenticationProvider implements AuthenticationProvider {
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

//        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtTokenUtil.getSecret());
//        Jws<Claims> jwsClaims = rawAccessToken.parseClaims("lzh");
        String subject = jwsClaims.getBody().getSubject();
//        jwsClaims.getBody().get
        List<String> roles = jwsClaims.getBody().get(jwtTokenUtil.CLAIM_KEY_ROLES, List.class);
        List<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        
        UserContext context = UserContext.create(subject, authorities);
        
        return new JwtAuthenticationToken(context, context.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
