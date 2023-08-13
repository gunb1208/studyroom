package com.myproject.service;

import java.util.List;

import com.myproject.domain.MemberDomain;

public interface MemberService {
	
	//회원목록 조회
	List<MemberDomain> selectAll();
	
	//회원가입
	void join(MemberDomain memberDomain);
	
	//아이디 중복체크
	public int idChk(MemberDomain memberDomain);
	
	//회원번호로 멤버객체찾기
	MemberDomain findByUserNo(int userNo);
	
	MemberDomain findByUserId(String userId);
	
	MemberDomain login(MemberDomain memberDomain);
	
}
