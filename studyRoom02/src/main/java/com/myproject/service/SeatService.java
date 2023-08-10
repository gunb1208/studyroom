package com.myproject.service;

import java.util.List;

import com.myproject.domain.SeatDomain;

public interface SeatService {

	// 모든 좌석 조회
	List<SeatDomain> getAllList();
	
	// 이용 가능한 빈 좌석 목록 조회
	List<SeatDomain> getEmptySeatList();
	
	// 좌석 상태 변경
	void changeStatus(SeatDomain seatDomain);
	
	
	
}
