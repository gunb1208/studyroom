package com.myproject.service;

import java.util.List;
import java.util.Map;

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
import xyz.sumtplus.adminDao.LockerDao;
import xyz.sumtplus.domain.LockerDomain;


/**
 * 관리자의 서비스Impl입니다
 *
 * @author 박재우
 * @Date   2021. 5. 18.
 */
@Service
@Log4j2
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
	private AdminDao adminDao;
	private MemberDao memberDao;
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
		
		Long userNo = memberDao.findUserNoBy(memberDomain.getUserId()); 
		
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
		adminDao.deleteLocker(regInfoDomain);
		adminDao.updateSeat(regInfoDomain);
		adminDao.updateLocker(regInfoDomain);
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
	public List<LockerDomain> getEmptyLockerList() {
		log.info("Service:: getEmptySeatList");
		return adminDao.getEmptyLockerList();
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
	
	

	/**
	 * 회원 중 좌석/사물함 미등록 회원 목록 조회
	 * @author 김보경
	 * @Date 2021. 5. 28.
	 */
	@Override
	public List<MemberDomain> getUnregListTest(Criteria cri) {
		return adminDao.getUnregList(cri);
	}

	/**
	 * 좌석배치도에 이용 중인 상태와 회원 이름과 만료일 보여주는 메서드
	 * (관리자-좌석관리에서 이용)
	 * @author 김보경
	 * @Date 2021. 5. 29.
	 */
	@Override
	public List<Map<String, Object>> showSeatPlanInfo() {
		log.warn(adminDao.getSeatPlanList());
		return adminDao.getSeatPlanList();
	}

	/**
	 * 좌석배치도에서 이용 중인 회원의 등록을 취소하는 메서드
	 * (관리자-좌석관리에서 이용)
	 * @author 김보경
	 * @Date 2021. 5. 31.
	 */
	@Override
	@Transactional
	public void revokeTheRegistration(Long userNo) {
		log.warn("등록 취소 될 회원 번호:: " + userNo);
		adminDao.deleteRegInfo(userNo);
		/* 이용 중이던 좌석/사물함 빈 상태로 변경 트랜잭션 */
		seatDao.delInfoupdateSeat(userNo);
		lockerDao.delInfoupdateLocker(userNo);
	}
	
	/**
	 * 사물함 배치도에 이용 중인 상태와 회원 이름과 만료일 보여주는 메서드
	 * (관리자-사물함관리에서 이용)
	 * @author 김보경
	 * @Date 2021. 6. 1.
	 */
	@Override
	public List<Map<String, Object>> showLockerPlanInfo() {
		log.warn(adminDao.getLockerPlanList());
		return adminDao.getLockerPlanList();
	}

	/**
	 * 사물함배치도에서 이용 중인 사물함 등록을 취소하는 메서드
	 * (관리자-사물함관리에서 이용)
	 * @author 김보경
	 * @Date 2021. 6. 1.
	 */
	@Override
	@Transactional
	public void revokeLocker(Long userNo) {
		log.warn(adminDao.deleteLocInfo(userNo));
		lockerDao.delInfoupdateLocker(userNo);
	}

	
}
