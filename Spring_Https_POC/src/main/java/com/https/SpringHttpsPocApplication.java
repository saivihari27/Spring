package com.https;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringHttpsPocApplication {
	
	@GetMapping("/getMessage")
	public String getMessage() {
		return "HTTPS Created";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringHttpsPocApplication.class, args);
	}

}
