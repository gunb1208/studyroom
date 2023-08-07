package com.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.domain.MemberDomain;
import com.myproject.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member/*")
@Log4j2
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/join")
	public void join() {
		
	}
	
	@PostMapping("/join")
	public String join(MemberDomain memberDomain, RedirectAttributes rttr) {
		log.info("================================");
		log.info("register: " + memberDomain);
		memberService.join(memberDomain);
		rttr.addFlashAttribute("result", memberDomain.getUserId());
		return "redirect:/member/login";
	}
	
	@GetMapping("/login")
	public void loginInput(){
		
	}
	
	@GetMapping("/test")
	public void test() {
		
	}
	
	
	
	
	
	
	
	
}
