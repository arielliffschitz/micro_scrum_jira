package com.ariel.mscrumjira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcSprintBacklogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcSprintBacklogApplication.class, args);
	}

}
