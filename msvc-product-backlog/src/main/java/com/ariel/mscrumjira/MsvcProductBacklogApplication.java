package com.ariel.mscrumjira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcProductBacklogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcProductBacklogApplication.class, args);
	}

}
