package com.myproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.myproject.domain.AuthDomain;
import com.myproject.domain.MemberDomain;

@Mapper
public interface MemberDao {
	
	//회원목록 조회
	public List<MemberDomain> selectAll();
	
	//회원가입
	void insert(MemberDomain memberDomain);
	
	//아이디 중복 체크
	public int idChk(MemberDomain memberDomain);
	
	//권한부여
	void insertAuth(AuthDomain authDomain);
	
	//시큐리티 username -> userid
	@Select("SELECT USERNO FROM MEMBER WHERE USERID = #{userId}")
	int findUserNoBy(@Param("userId") String userId);
	
	MemberDomain read(int userNo);
	
	MemberDomain findById(String userId);
	
	MemberDomain login(MemberDomain memberDomain);
}
