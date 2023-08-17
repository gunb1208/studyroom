package com.myproject.service;

import java.util.List;

import com.myproject.domain.BoardDomain;
import com.myproject.domain.Criteria;

public interface BoardService {
	
	public void register(BoardDomain board);
	
	public BoardDomain get(Integer bno);
	
	public boolean modify(BoardDomain board);
	
	public boolean remove(Integer bno);
	
	public List<BoardDomain> getList(Criteria cri);
	
	//Criteria
	public int getTotalCount(Criteria cri);
	
	public int getNoticeTotalCount(Criteria cri);

	public int getQATotalCount(Criteria cri);
	
	
	int increaseHitCount(Integer bno);
}
