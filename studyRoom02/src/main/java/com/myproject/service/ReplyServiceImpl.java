package com.myproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.dao.BoardDao;
import com.myproject.dao.ReplyDao;
import com.myproject.domain.Criteria;
import com.myproject.domain.ReplyDomain;
import com.myproject.domain.ReplyPageDTO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Log4j2
@Transactional
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private BoardDao boardMapper;
	@Autowired
	private ReplyDao replyMapper;
	
	@Override
	public int register(ReplyDomain vo) {
		log.info("register :: "+vo);
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return replyMapper.insert(vo);
	}

	@Override
	public ReplyDomain get(Integer rno) {
		log.info("get :: "+rno);
		return replyMapper.read(rno);
	}

	@Override
	public int modify(ReplyDomain vo) {
		log.info("modify :: "+vo);
		return replyMapper.update(vo);
	}

	@Override
	public int remove(Integer rno) {
		log.info("remove :: "+rno);
		boardMapper.updateReplyCnt(get(rno).getBno(), -1);
		return replyMapper.delete(rno);
	}

	@Override
	public List<ReplyDomain> getList(Criteria cri, Integer bno) {
		log.info("getList :: {" + bno + "}, {" + cri + "}");
		return replyMapper.getListWithPaging(cri, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Integer bno) {
		return new ReplyPageDTO(cri, 
				replyMapper.getTotalCount(cri, bno),
				replyMapper.getListWithPaging(cri, bno));
	}

	@Override
	public List<ReplyDomain> getListMore(Integer rno, Integer bno) {
		return replyMapper.getListWithShowMore(rno, bno);
	}
	
}
