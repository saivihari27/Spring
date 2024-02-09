package com.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class EmployeeSecurityConfig {

	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetails() {
		String pass1 = passwordEncode().encode("sai123");
		UserDetails admin = User.withUsername("sai")
				.password(pass1)
				.roles("ADMIN")
				.build();
		System.out.println("encrypt password is "+pass1);
		UserDetails user = User.withUsername("saivihari")
				.password(passwordEncode().encode("sai456"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(admin, user);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable);
		
		return http.authorizeHttpRequests(request -> { request
				.requestMatchers("/saveemployees","/allemployees","/updateemployee").permitAll()
				.requestMatchers("/employees/{id}","/employees").authenticated();
		})
		.httpBasic(Customizer.withDefaults())
				.build();	
	}
}
