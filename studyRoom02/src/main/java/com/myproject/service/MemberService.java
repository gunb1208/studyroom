package com.myproject.service;

import java.util.List;

import com.myproject.domain.MemberDomain;

public interface MemberService {
	
	List<MemberDomain> selectAll();
	
	void join(MemberDomain memberDomain);
	
}
