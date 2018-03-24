package com.jwtdemo.jwt.security;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Hadi
 *
 */
@Service
public class JwtAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		String token = auth.getPrincipal().toString();
		// extract the user from token using jjwt library and validate the token is related to that user
		return new UsernamePasswordAuthenticationToken(/*this should be replaced by the username*/auth.getPrincipal(),null,/*user roles- empty for now*/new ArrayList<>());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(Authentication.class);
	}

	
}
