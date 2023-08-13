package com.myproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myproject.domain.SeatDomain;

@Mapper
public interface SeatDao {

	// 모든 좌석 조회
	List<SeatDomain> getAllList();
	
	// 이용 가능한 빈 좌석 목록 조회
	List<SeatDomain> getEmptySeatList();
	
	// 좌석 상태 변경
	void updateStatus(SeatDomain seatDomain);
	
	// 좌석 변경 시 새 좌석 상태 변경(이용중)
	void updateNewSeat(SeatDomain seatDomain);
	
	// 좌석 변경 시 기존 좌석 상태 변경(빈좌석)
	void updateOldSeat(SeatDomain seatDomain);
	
	// 이용 등록 정보 삭제 시 빈 좌석으로 변경
	void delInfoupdateSeat(int userNo);
	
	
	
	
}
