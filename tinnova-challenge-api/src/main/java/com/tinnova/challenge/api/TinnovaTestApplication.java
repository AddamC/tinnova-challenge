package com.tinnova.challenge.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class TinnovaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinnovaTestApplication.class, args);
	}

}