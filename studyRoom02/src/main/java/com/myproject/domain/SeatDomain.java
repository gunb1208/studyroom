package com.myproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDomain {

	private int sno; 		// 좌석 번호
	private String name;	// 좌석 이름(고정석,자유석)
	private boolean status; // 좌석 이용 상태
	private int userNo; 	// 회원 번호
	
}
