package com.myproject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping("/test")
	public void test() {
		
	}
	
	@GetMapping("/login")
	public void loginInput(){
		
	}
	
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
	
	@RequestMapping("/idcheck")
	@ResponseBody
	public Map<Object, Object> idcheck(@RequestBody MemberDomain memberDomain) {
		log.info(memberDomain); 
		int count = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();
		 
		count = memberService.idChk(memberDomain);
		map.put("cnt", count);
		 
		return map;
	}
	
	
	
	
	
	
	
	
	
	
}
