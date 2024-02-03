package com.bootapp; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bootapp.*"})
@Configuration
public class MultiDbPocApplication {
	
	@Bean
	SampleRunner sampleRunner() {
		return new SampleRunner();
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(MultiDbPocApplication.class, args);
	}


}
