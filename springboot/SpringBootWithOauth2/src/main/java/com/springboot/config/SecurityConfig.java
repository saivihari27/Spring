package com.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
		httpSecurity.authorizeHttpRequests()
		.anyRequest()
		.authenticated()
		.httpBasic();
		return httpSecurity.build();
		
	}
}
