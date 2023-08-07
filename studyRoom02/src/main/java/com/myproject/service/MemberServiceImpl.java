package com.myproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.dao.MemberDao;
import com.myproject.domain.MemberDomain;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;
	
	@Override
	public List<MemberDomain> selectAll() {
		
		return memberDao.selectAll();
	}

	@Override
	public void join(MemberDomain memberDomain) {

		memberDao.insert(memberDomain);
	}
	
}
