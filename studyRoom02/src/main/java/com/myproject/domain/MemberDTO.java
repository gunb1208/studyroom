package com.myproject.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDTO {
	
	private String userId;
	private String userPw;

}
