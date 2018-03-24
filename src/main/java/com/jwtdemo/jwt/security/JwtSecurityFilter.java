package com.jwtdemo.jwt.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtSecurityFilter extends OncePerRequestFilter{
	
	
	private AuthenticationProvider authenticationProvider;

	public JwtSecurityFilter(AuthenticationProvider authenticationProvider){
		this.authenticationProvider = authenticationProvider;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String token = request.getHeader("Authorization").replace("Bearer ", "");
		Authentication auth = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(token, null));
		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
		ctx.setAuthentication(auth);
		SecurityContextHolder.setContext(ctx);
		chain.doFilter(request, response);
	}

}
