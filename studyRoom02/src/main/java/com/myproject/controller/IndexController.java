package com.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@AllArgsConstructor
public class IndexController {

	@GetMapping("/index")
	public void index() {
		log.info("index... start");
		log.info("indexList......");

	}
	
	@GetMapping("/")
	public String home() {
		log.info("index... start");
		log.info("indexList......");
		
		return "/index";
	}

	@GetMapping("/innerPage")
	public void innerPage() {
		log.info("innerPage..");
	}

	@GetMapping("/aboutUs")
	public void aboutUs() {
		log.info("aboutUs..");
	}

	@GetMapping("/gallery")
	public void gallery() {
		log.info("gallery..");
	}
	
	@GetMapping("/ysStudyRoom")
	public void ysStudyRoom() {
		log.info("ysStudyRoom..");
	}
	
}
