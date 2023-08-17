package com.myproject.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.myproject.domain.Criteria;
import com.myproject.domain.ReplyDomain;

@Mapper
public interface ReplyDao {
	//입력
	int insert(ReplyDomain vo);
	int insert2(Map<String, Object> vo);
	
	ReplyDomain read(Integer rno);
	
	//수정
	int update(ReplyDomain vo);
	
	//삭제
	int delete(Integer rno);
	
	List<ReplyDomain> getListWithPaging(
			@Param("cri") Criteria cri, @Param("bno") Integer bno);
	
	List<Map<String, Object>> getListWithPaging2(
			@Param("cri") Criteria cri, @Param("bno") Integer bno);
	
	int getTotalCount(@Param("cri") Criteria cri, @Param("bno") Integer bno);

	List<ReplyDomain> getListWithShowMore(@Param("rno") Integer rno, @Param("bno") Integer bno);
	
}
