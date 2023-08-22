package com.myproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.myproject.domain.AddressDomain;
import com.myproject.domain.AuthDomain;
import com.myproject.domain.MemberDTO;
import com.myproject.domain.MemberDomain;

@Mapper
public interface MemberDao {
	
	//회원목록 조회
	public List<MemberDomain> selectAll();
	
	//회원가입
	void insert(MemberDomain memberDomain);
	
	//아이디 중복 체크
	public int idChk(MemberDomain memberDomain);
	
	//주소
	void insertAddr(AddressDomain addressVO);
	
	//권한부여
	void insertAuth(AuthDomain authDomain);
	
	//탈퇴
	@Delete("DELETE MEMBER WHERE USERNO = #{userNo}")
	int delete(int userNo);
	
	//권한삭제
	void deleteAuth(int userNo);
	
	//계정 비활성화
	@Update("UPDATE MEMBER SET ENABLED = 0 WHERE USERNO = #{userNo}")
	int update(int userNo);
	
	//시큐리티 username -> userid
	@Select("SELECT USERNO FROM MEMBER WHERE USERID = #{userId}")
	int findUserNoBy(@Param("userId") String userId);
	
	MemberDomain read(int userNo);
	
	MemberDomain findById(String userId);
	
	MemberDomain login(MemberDTO memberDTO);
}
