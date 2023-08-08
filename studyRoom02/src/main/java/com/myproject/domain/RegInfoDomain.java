package com.myproject.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegInfoDomain {
	
	private int regNo; // 등록번호
	
	private int userNo; // 회원번호
	private String userName; // 회원 이름
	
	private Date startDate; // 시작일
	private Date endDate; // 종료일
	
	private int fno; // 요금제 번호
	
	private int seatNo; // 좌석번호
	private String feeName; // 좌석 기간명
	private SeatDomain seat; // 좌석 정보 불러오기
	
	private int period; // 기간
}
