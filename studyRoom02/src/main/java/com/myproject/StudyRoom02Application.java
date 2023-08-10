package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = {"com.myproject.security.CustomUserDetailsService"})
@ComponentScan(basePackages = {"com.myproject.dao.SeatDao"})
@SpringBootApplication
public class StudyRoom02Application {

	public static void main(String[] args) {
		SpringApplication.run(StudyRoom02Application.class, args);
	}

}
