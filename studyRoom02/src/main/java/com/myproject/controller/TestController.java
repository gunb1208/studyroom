package com.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.domain.MemberDomain;
import com.myproject.service.MemberService;

@RestController
public class TestController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/member/")
	public @ResponseBody List<MemberDomain> selectMember() throws Exception{
		
		List<MemberDomain> domains = memberService.selectAll();
		
		return domains;
	}
	
	
	
	
	
	
	
	
}
