package com.myproject.service;

import java.util.List;
import java.util.Map;

import com.myproject.domain.AddressDomain;
import com.myproject.domain.BoardDomain;
import com.myproject.domain.Criteria;
import com.myproject.domain.MemberDomain;
import com.myproject.domain.PaymentDomain;
import com.myproject.domain.RegInfoDomain;
import com.myproject.domain.SeatDomain;

public interface AdminService {
	
	List<RegInfoDomain> itemStatus(); // 상품 현황 조회 
	
	List<MemberDomain> memberList(Criteria cri); // 회원 목록 조회
	
	List<MemberDomain> memberContactList(Criteria cri); // 회원 목록 조회
	
	List<PaymentDomain> paymentList(Criteria cri); // 결제 내역 조회
	
	List<RegInfoDomain> regProperties(Criteria cri); // 등록 정보 조회
	
	RegInfoDomain regDetail(int regNo); // 등록 상세정보 조회
	
	MemberDomain memberDetail(int userNo); // 번호로 회원 조회
	
	AddressDomain memberAddr(int userNo); // 번호로 회원 주소 조회
	
	void updateMember(MemberDomain memberDomain); // 회원 정보 수정
	
	void updateRegPP(RegInfoDomain regInfoDomain); // 등록 정보 수정
	
	int getMemberTotal(Criteria cri); // 총 회원 수
	
	int getMemberContactTotalCount(Criteria cri); // 연락할 회원 총 수
	
	int getRegTotalCount(Criteria cri); // 총 등록 수
	
	int getPaymentTotalCount(Criteria cri); // 총 결제내역 수
	
	List<BoardDomain> getNotiList(); // 공지사항 20개 불러옴
	
	List<BoardDomain> getQAList(); // Q&A 20개 불러옴
	
	int getTotalImcome(); // 현재까지의 매출액
	
	// 이용 가능한 빈 좌석 목록 조회
	List<SeatDomain> getEmptySeatList();
	
	
	MemberDomain sendMessage(); // 5일 남은 회원 불러오기
	
	List<MemberDomain> getUnregListTest(Criteria cri);
	
	List<Map<String, Object>> showSeatPlanInfo();
	
	void revokeTheRegistration(int userNo);
	
	
}
