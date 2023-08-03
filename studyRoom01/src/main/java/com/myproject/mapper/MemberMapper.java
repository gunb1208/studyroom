package com.myproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.myproject.domain.Member;

@Mapper
public interface MemberMapper {

//	@Select("SELECT * FROM member")
//    List<Member> findAll();
//
//    @Select("SELECT * FROM member WHERE userno = #{userno}")
//    Member findByUserNo(@Param("userno") int userNo);
//    
//    @Insert("INSERT INTO member(userid, userpw, username, email, tel) VALUES(#{userid}, #{userpw}, #{username}, #{email}, #{tel}")
//    @Options(useGeneratedKeys = true, keyProperty = "userno")
//    int save(@Param("member") final Member member);
	
	List<Member> findAll();
	
	void userAdd(Member member);
    
}
