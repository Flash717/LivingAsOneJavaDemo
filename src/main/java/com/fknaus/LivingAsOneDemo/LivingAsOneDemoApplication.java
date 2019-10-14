package com.fknaus.LivingAsOneDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.fknaus.LivingAsOneDemo")
public class LivingAsOneDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivingAsOneDemoApplication.class, args);
	}

}
