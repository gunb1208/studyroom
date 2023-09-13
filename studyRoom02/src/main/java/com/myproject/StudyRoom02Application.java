package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StudyRoom02Application {

	public static void main(String[] args) {
		
		SpringApplication.run(StudyRoom02Application.class, args);
		
	}

}
