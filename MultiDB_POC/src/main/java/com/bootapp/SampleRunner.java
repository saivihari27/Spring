package com.bootapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello Spring Boot App");	
	}

}
