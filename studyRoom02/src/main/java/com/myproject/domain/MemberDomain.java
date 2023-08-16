package com.myproject.domain;

import java.util.List;

import lombok.Data;

@Data
public class MemberDomain {
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String email;
	private String tel;
	private boolean enabled;
	

	private AddressDomain address; //회원 주소
	private List<AuthDomain> authList; //권한
    
    private String endDate; //서비스 종료일
    private RegInfoDomain regInfo;
}
