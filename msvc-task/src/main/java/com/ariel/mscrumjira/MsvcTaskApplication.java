package com.ariel.mscrumjira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcTaskApplication.class, args);
	}

}
