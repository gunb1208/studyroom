package com.myproject.domain;

import lombok.Data;

@Data
public class Member {
	
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String email;
	private String tel;
	private boolean enabled;
	
}
