package com.lzh.zuulservice.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzh.common.StatusCode;
import com.lzh.common.model.dto.response.ErrorEntity;

/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    
    @Autowired
    private ObjectMapper mapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
//		if (e instanceof BadCredentialsException) {
//			mapper.writeValue(response.getWriter(), ErrorResponse.of("Invalid username or password", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
//		} else if (e instanceof JwtExpiredTokenException) {
//			mapper.writeValue(response.getWriter(), ErrorResponse.of("Token has expired", ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
//		} else if (e instanceof AuthMethodNotSupportedException) {
//		    mapper.writeValue(response.getWriter(), ErrorResponse.of(e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
//		}
//
		
		mapper.writeValue(response.getWriter(), new ErrorEntity(StatusCode.UNAUTHORIZED,e.getMessage()));
	}
}
