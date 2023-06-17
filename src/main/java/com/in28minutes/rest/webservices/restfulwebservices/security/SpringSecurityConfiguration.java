package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
//		1) All requests should be authenticated
		http.authorizeHttpRequests(
				auth -> auth.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated()
				);
//		2) If a request is not authenticated, use http basic
		http.httpBasic(Customizer.withDefaults());
		
		http.sessionManagement(
				session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
//		3) CSRF -> POST, PUT
		http.csrf().disable();

		
		return http.build();
	}

}