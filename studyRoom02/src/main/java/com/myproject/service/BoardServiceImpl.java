package com.myproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.dao.BoardDao;
import com.myproject.domain.BoardDomain;
import com.myproject.domain.Criteria;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao mapper;
	
	@Override
	@Transactional
	public BoardDomain get(Integer bno) {
		log.info("get......" + bno);
		mapper.updateHitCount(bno); // 조회수 업데이트 추가
		return mapper.read(bno);
	}

	@Override
	@Transactional
	public boolean modify(BoardDomain board) {
		log.info("modify......." + board);
		return mapper.update(board) == 1;
	}

	@Override
	@Transactional
	public boolean remove(Integer bno) {
		log.info("remove......." + bno);
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardDomain> getList(Criteria cri) {
		log.info("답글 포함 List..");
		return mapper.getList2(cri);
	}

	@Override
	@Transactional
	public void register(BoardDomain board) {
		log.info("register......" + board);
		if(board.getParentNo() == null) {
			board.setParentNo(0) ;
		}
		mapper.insertSelectKey(board);
	}



	@Override
	public int getTotalCount(Criteria cri) {
		log.warn("getTotal... ");
		return mapper.getTotalCount(cri);
	}

	@Override
	public int getNoticeTotalCount(Criteria cri) {
		log.warn("NoticeTotal... ");
		return mapper.getNoticeTotalCount(cri);
	}

	@Override
	public int getQATotalCount(Criteria cri) {
		log.warn("Q&ATotal.... ");
		return mapper.getQATotalCount(cri);
	}

	@Override
	public int increaseHitCount(Integer bno) {
		log.warn("조회한 글 번호:: " + bno);
		return mapper.updateHitCount(bno);
	}

	
	
}
