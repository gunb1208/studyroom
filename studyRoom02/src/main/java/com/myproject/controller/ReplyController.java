package com.myproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.domain.Criteria;
import com.myproject.domain.ReplyDomain;
import com.myproject.domain.ReplyPageDTO;
import com.myproject.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/replies/")
@Log4j2
@AllArgsConstructor
public class ReplyController {
	@Autowired
	private ReplyService service;
	
	//등록
	@PostMapping("new")
//	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> create(@RequestBody ReplyDomain vo){
		log.warn(vo);
		int insertCount = service.register(vo);
		log.info("insertCount :: " + insertCount);
		return insertCount == 1 ?
				new ResponseEntity<>(vo.getRno().toString(),HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//조회
	@GetMapping("{rno}")
	public ResponseEntity<ReplyDomain> get(@PathVariable Integer rno){
		log.info(rno);
		return new ResponseEntity<ReplyDomain>(service.get(rno),HttpStatus.OK);
	}
	
	//수정
	@RequestMapping(value="{rno}", method={RequestMethod.PUT, RequestMethod.PATCH})
//	@PreAuthorize("principal.username == #vo.replyer")
	public ResponseEntity<String> modify(@PathVariable("rno") Integer rno, @RequestBody ReplyDomain vo){
		log.info(vo);
		vo.setRno(rno);
		int updateCount = service.modify(vo);
		log.info("insertCount :: " + updateCount);
		return updateCount == 1 ?
				new ResponseEntity<>("success",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//삭제
	@DeleteMapping(value= "{rno}/{replyer}", produces = {MediaType.TEXT_PLAIN_VALUE})
//	@PreAuthorize("principal.username == #replyer")
	public ResponseEntity<String> remove(@PathVariable("rno") Integer rno, @PathVariable String replyer){
		int removeCount = service.remove(rno);
		log.info("insertCount :: " + removeCount);
		return removeCount == 1?
				new ResponseEntity<>("success",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//페이지(목록)
	@GetMapping("pages/{page}/{bno}")
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable int page, @PathVariable int bno){
		log.info("getList");
		Criteria cri = new Criteria(page, 10);
		log.info(cri);
		return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
	}
	
	
	//페이지(더보기)
	@GetMapping({"more/{bno}","more/{bno}/{rno}"})
	public List<ReplyDomain> getListMore(@PathVariable Integer bno, @PathVariable Optional<Integer> rno){
		log.info("getListMore");
		return service.getListMore(rno.isPresent() ? rno.get() : null, bno);
	}
	
}
