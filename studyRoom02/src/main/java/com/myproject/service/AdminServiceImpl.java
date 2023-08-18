package com.myproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.dao.AdminDao;
import com.myproject.dao.MemberDao;
import com.myproject.dao.SeatDao;
import com.myproject.domain.AddressDomain;
import com.myproject.domain.BoardDomain;
import com.myproject.domain.Criteria;
import com.myproject.domain.MemberDomain;
import com.myproject.domain.PaymentDomain;
import com.myproject.domain.RegInfoDomain;
import com.myproject.domain.SeatDomain;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@Service
//@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private SeatDao seatDao;
	
	public List<RegInfoDomain> itemStatus() {
		// TODO Auto-generated method stub
		log.warn("itemStatus()...");
		
		return adminDao.itemStatus();
	}

	@Override
	public List<MemberDomain> memberList(Criteria cri) {
		// TODO Auto-generated method stub
		log.warn("memberList()...");
		
		return adminDao.memberList(cri);
	}

	@Override
	public List<PaymentDomain> paymentList(Criteria cri) {
		// TODO Auto-generated method stub
		log.warn("paymentList()...");
		
		return adminDao.paymentList(cri);
	}

	@Override
	public List<RegInfoDomain> regProperties(Criteria cri) {
		// TODO Auto-generated method stub
		log.warn("regProperties()...");
		
		return adminDao.regProperties(cri);
	}

	@Override
	public MemberDomain memberDetail(int userNo) {
		// TODO Auto-generated method stub
		log.warn("memberDetail()...");
		
		return adminDao.memberDetail(userNo);
	}

	@Override
	public AddressDomain memberAddr(int userNo) {
		// TODO Auto-generated method stub
		log.warn("회원의 주소");
		return adminDao.memberAddr(userNo);
	}

	@Override
	@Transactional
	public void updateMember(MemberDomain memberDomain) {
		// TODO Auto-generated method stub
		log.warn("updateMember()...");
		adminDao.updateMember(memberDomain);
		
		int userNo = memberDao.findUserNoBy(memberDomain.getUserId()); 
		
		memberDomain.getAddress().setUserNo(userNo);
		log.warn(memberDomain.getAddress());
		adminDao.deleteAddr(memberDomain);
		adminDao.updateAddr(memberDomain.getAddress());
	}

	
	@Override
	@Transactional
	public void updateRegPP(RegInfoDomain regInfoDomain) {
		// TODO Auto-generated method stub
		log.warn("updateRegPP()....");
		adminDao.updateRegPP(regInfoDomain);
		adminDao.deleteSeat(regInfoDomain);
		adminDao.updateSeat(regInfoDomain);
	}

	@Override
	public RegInfoDomain regDetail(int regNo) {
		// TODO Auto-generated method stub
		log.warn("register detail...");
		return adminDao.regDetail(regNo);
	}

	@Override
	public int getMemberTotal(Criteria cri) {
		// TODO Auto-generated method stub
		log.warn("get total count...");
		return adminDao.getMemberTotalCount(cri);
	}

	@Override
	public int getRegTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		log.warn("get total count...");
		return adminDao.getRegTotalCount(cri);
	}

	@Override
	public int getPaymentTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		log.warn("get total count...");
		return adminDao.getPaymentTotalCount(cri);
	}

	@Override
	public MemberDomain sendMessage() {
		// TODO Auto-generated method stub
		log.warn("5일 남은 회원을 불러옵니다...");
		return adminDao.sendMessage();
	}

	@Override
	public List<MemberDomain> memberContactList(Criteria cri) {
		// TODO Auto-generated method stub
		log.warn("기간을 기준으로 회원을 불러옵니다...");
		return adminDao.memberContactList(cri);
	}

	@Override
	public int getMemberContactTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		log.warn("get Contact total count...");
		return adminDao.getMemberContactTotalCount(cri);
	}
	
	@Override
	public List<SeatDomain> getEmptySeatList() {
		log.info("Service:: getEmptySeatList");
		return adminDao.getEmptySeatList();
	}
	
	@Override
	public List<BoardDomain> getNotiList() {
		log.warn("관리자가 공지사항을 불러옵니다");
		return adminDao.getNotiList();
	}
	
	@Override
	public List<BoardDomain> getQAList() {
		log.warn("관리자가 Q&A를 불러옵니다");
		return adminDao.getQAList();
	}
	
	@Override
	public int getTotalImcome() {
		log.warn("현재까지 번 돈을 불러옵니다");
		return adminDao.getTotalImcome();
	}
	
	

	@Override
	public List<MemberDomain> getUnregListTest(Criteria cri) {
		return adminDao.getUnregList(cri);
	}

	@Override
	public List<Map<String, Object>> showSeatPlanInfo() {
		log.warn(adminDao.getSeatPlanList());
		return adminDao.getSeatPlanList();
	}
	
	@Override
	@Transactional
	public void revokeTheRegistration(int userNo) {
		log.warn("등록 취소 될 회원 번호:: " + userNo);
		adminDao.deleteRegInfo(userNo);
		/* 이용 중이던 좌석/사물함 빈 상태로 변경 트랜잭션 */
		seatDao.delInfoupdateSeat(userNo);
	}

	


	
}
