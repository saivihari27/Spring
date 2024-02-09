package com.springboot.controller;

import org.springframework.security.config.annotation.web.oauth2.client.OAuth2ClientSecurityMarker;
import org.springframework.security.config.annotation.web.oauth2.login.OAuth2LoginSecurityMarker;
import org.springframework.security.config.annotation.web.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OAuth2ClientSecurityMarker
@OAuth2LoginSecurityMarker
@OAuth2ResourceServerSecurityMarker
public class HelloController {
	
	@GetMapping("/hello")
	public String message() {
		return "Hello Spring";
	}
}
