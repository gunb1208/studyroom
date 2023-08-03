package com.myproject.service;

import org.springframework.stereotype.Service;

import com.myproject.mapper.MemberMapper;

@Service
public class MemberService {
	
	private MemberMapper memberMapper;
	
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	
}
