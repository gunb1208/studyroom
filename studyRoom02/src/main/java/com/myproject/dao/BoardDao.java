package com.myproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.myproject.domain.BoardDomain;
import com.myproject.domain.Criteria;

@Mapper
public interface BoardDao {

	// 게시글 조회
	public List<BoardDomain> getList();
	public List<BoardDomain> getListWithPaging(Criteria cri);	
	public List<BoardDomain> getList2(Criteria cri);
	
	// 작성
	public void insert(BoardDomain boardVO);
	public Integer insertSelectKey(BoardDomain boardVO);
	public Integer insertSelectKey2(BoardDomain boardVO);
	
	// 삭제
	public int delete(Integer bno);
	
	// 수정
	public int update(BoardDomain boardVO);	

	// 상세
	public BoardDomain read(Integer bno);
	
	//Criteria
	public int getTotalCount(Criteria cri);
	
	public int getNoticeTotalCount(Criteria cri);

	public int getQATotalCount(Criteria cri);

	@Update("UPDATE TBL_BOARD SET REPLYCNT = REPLYCNT + #{amount} WHERE BNO = #{bno}")
	void updateReplyCnt(@Param("bno") Integer bno, @Param("amount") int amount);
	
	public int updateHitCount(Integer bno);

}