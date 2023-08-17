package com.myproject.service;

import java.util.List;

import com.myproject.domain.Criteria;
import com.myproject.domain.ReplyDomain;
import com.myproject.domain.ReplyPageDTO;


public interface ReplyService {
	int register(ReplyDomain vo);

	ReplyDomain get(Integer rno);
	
	int modify(ReplyDomain vo);
	
	int remove(Integer rno);
	
	List<ReplyDomain> getList(Criteria cri, Integer bno);
	
	ReplyPageDTO getListPage(Criteria cri, Integer bno);

	List<ReplyDomain> getListMore(Integer rno, Integer bno);
}
