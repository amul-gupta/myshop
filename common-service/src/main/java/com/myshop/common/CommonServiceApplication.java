package com.myshop.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommonServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(CommonServiceApplication.class, args);
		System.out.println("Welcome to common service!!");
	}

}
