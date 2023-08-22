package com.myproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.dao.AdminDao;
import com.myproject.dao.MemberDao;
import com.myproject.dao.PaymentDao;
import com.myproject.dao.SeatDao;
import com.myproject.domain.AuthDomain;
import com.myproject.domain.MemberDTO;
import com.myproject.domain.MemberDomain;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;
	@Autowired
	SeatDao seatDao;
	@Autowired
	AdminDao adminDao;
	@Autowired
	PaymentDao paymentDao;
	
	
	@Override
	public List<MemberDomain> selectAll() {
		
		return memberDao.selectAll();
		
	}

	@Override
	public void join(MemberDomain memberDomain) {
		
		AuthDomain authDomain = new AuthDomain();
		
		log.info("service register..." + memberDomain);
		
//		String encodePw = encoder.encode(memberDomain.getUserPw());
//		memberDomain.setUserPw(encodePw);

		memberDao.insert(memberDomain);
		
		// 회원 번호 조회
		int userNo = memberDao.findUserNoBy(memberDomain.getUserId());
		authDomain.setUserNo(userNo);
		
		// 회원 권한 입력
		memberDao.insertAuth(authDomain);

		// 회원 주소 입력
		memberDomain.getAddress().setUserNo(userNo);
		memberDao.insertAddr(memberDomain.getAddress());
		
	}

	@Override
	public int idChk(MemberDomain memberDomain) {
		
		int result = memberDao.idChk(memberDomain);
		return result;
		
	}
	
	@Override
	public int withdraw(int userNo) {
		log.warn("등록 취소 될 회원 번호:: " + userNo);
		
		paymentDao.delete(userNo);
		adminDao.deleteRegInfo(userNo);
		seatDao.delInfoupdateSeat(userNo);
		
		memberDao.deleteAuth(userNo);
		
		return memberDao.delete(userNo);
	}

	@Transactional
	@Override
	public int deactivate(int userNo) {
		log.warn("등록 취소 될 회원 번호:: " + userNo);
		
		paymentDao.delete(userNo);
		adminDao.deleteRegInfo(userNo);
		seatDao.delInfoupdateSeat(userNo);

		return memberDao.update(userNo);
	}

	@Override
	public MemberDomain findByUserNo(int userNo) {
		
		return memberDao.read(userNo);
	}

	@Override
	public MemberDomain findByUserId(String userId) {
		
		return memberDao.findById(userId);
	}

	@Override
	public MemberDomain login(MemberDTO memberDTO) {
		
		return memberDao.login(memberDTO);
	}
	
}
