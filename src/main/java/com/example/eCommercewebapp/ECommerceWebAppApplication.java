package com.example.eCommercewebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication

@EntityScan("com.example.eCommercewebapp.model")
public class ECommerceWebAppApplication {


	public static void main(String[] args) {
		SpringApplication.run(ECommerceWebAppApplication.class, args);
	}

}
