package com.myproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myproject.dao.MemberDao;
import com.myproject.domain.AuthDomain;
import com.myproject.domain.MemberDomain;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;

//	@Autowired
//	private PasswordEncoder encoder;
	
	
	
	@Override
	public List<MemberDomain> selectAll() {
		
		return memberDao.selectAll();
		
	}

	@Override
	public void join(MemberDomain memberDomain) {
		
//		AuthDomain authDomain = new AuthDomain();
//		
//		log.info("service register..." + memberDomain);
//		
//		String encodePw = encoder.encode(memberDomain.getUserPw());
//		memberDomain.setUserPw(encodePw);
//
//		memberDao.insert(memberDomain);
//		
//		// 회원 번호 조회
//		int userNo = memberDao.findUserNoBy(memberDomain.getUserId());
//		authDomain.setUserNo(userNo);
//		
//		// 회원 권한 입력
//		memberDao.insertAuth(authDomain);
		
	}

	@Override
	public int idChk(MemberDomain memberDomain) {
		
		int result = memberDao.idChk(memberDomain);
		return result;
		
	}

	@Override
	public MemberDomain findByUserNo(int userNo) {
		
		return memberDao.read(userNo);
	}

	@Override
	public MemberDomain findByUserId(String userId) {
		
		return memberDao.findById(userId);
	}
	
}
