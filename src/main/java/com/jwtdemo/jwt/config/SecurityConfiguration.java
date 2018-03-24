package com.jwtdemo.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwtdemo.jwt.security.JwtSecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Autowired
	AuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			//register the filter and pass the auth provider
			.addFilterBefore(new JwtSecurityFilter(authenticationProvider), UsernamePasswordAuthenticationFilter.class)
			
			//dont create session
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			//all requests should be protected
			.authorizeRequests()
			.anyRequest()
			.authenticated();
	}

}
