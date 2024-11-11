package com.backend.positions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PositionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PositionsApplication.class, args);
	}

}
