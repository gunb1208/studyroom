package com.myproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myproject.domain.RegInfoDomain;

@Mapper
public interface RegInfoDao {

	// 이용 등록 정보 등록
	int insertRegInfo(RegInfoDomain regInfoDomain);
	
	// 이용 등록 정보 상세 조회
	RegInfoDomain getDetail(int userNo);
	
	// 이용 등록 정보 목록 조회
	List<RegInfoDomain> getAllList();
	
	// 좌석 변경 (이용 등록 정보 수정)
	public int updateSeat(RegInfoDomain regInfoDomain);
	
	// 만료일 지난 정보 목록
	List<RegInfoDomain> getExpireInfo();
	
	// 만료일 지난 정보 삭제
	int deleteRegInfo();
	
	// 이용 기간 연장 (이용 등록 정보 수정)
	int updatePeriod(RegInfoDomain regInfoDomain);
	
	
	
	
	
	// -------------- 임시 테이블 --------------
	
	// 결제 전 임시 테이블에 등록
	int insertTmpReg(RegInfoDomain infoDomain);
	
	// 임시 테이블 데이터 조회
	List<RegInfoDomain> getTmpList();
	

	// 임시 등록 데이터 삭제
	int deleteTmpReg();

	// 결제 미완료 된 임시 등록 정보 목록
	List<RegInfoDomain> getTmpToRegList();
	
}
