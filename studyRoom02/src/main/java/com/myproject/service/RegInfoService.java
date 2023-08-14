package com.myproject.service;

import java.util.List;

import com.myproject.domain.RegInfoDomain;
import com.myproject.domain.SeatDomain;

public interface RegInfoService {
	
	// 이용 등록 정보 등록
	int RegisterRegInfo(RegInfoDomain regInfoDomain);

	// 이용 등록 정보 상세 조회
	RegInfoDomain read(int userNo);

	// 이용 등록 정보 목록 조회
	List<RegInfoDomain> getAllList();
	
	// 좌석 변경 (이용 등록 정보 수정)
	void changeSeat(RegInfoDomain regInfoDomain, SeatDomain seatDomain);
	
	// 이용 기간 연장
	int extendUsingPeriod(RegInfoDomain regInfoDomain);
	

	// 결제 전 임시 등록
	int RegisterTmpReg(RegInfoDomain infoDomain);
	
}
