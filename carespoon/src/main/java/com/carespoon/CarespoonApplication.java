package com.carespoon;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CarespoonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarespoonApplication.class, args);
	}

}
