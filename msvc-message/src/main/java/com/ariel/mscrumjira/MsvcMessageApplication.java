package com.ariel.mscrumjira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.*;

@SpringBootApplication
@EnableFeignClients
public class MsvcMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcMessageApplication.class, args);
	}

}
