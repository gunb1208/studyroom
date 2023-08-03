package com.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myproject.domain.Member;
import com.myproject.mapper.MemberMapper;

@Controller
public class MemberController {

	@Autowired
	private MemberMapper memberMapper;
	
	@RequestMapping("/member")
	public @ResponseBody List<Member> memberlist() throws Exception {
		
		List<Member> memberlist = memberMapper.findAll();
		
		return memberlist;
		
	}
	
	
}
