package com.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.domain.BoardDomain;
import com.myproject.domain.Criteria;
import com.myproject.domain.PageDTO;
import com.myproject.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model){
		log.info("게시판 페이지");
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(service.getTotalCount(cri), cri));
		model.addAttribute("pageMaker1", new PageDTO(service.getNoticeTotalCount(cri), cri));
		model.addAttribute("pageMaker2", new PageDTO(service.getQATotalCount(cri), cri));
		log.warn("category : " + cri.getCategory());
		//model.addAttribute("category", cri.getCategory());
	}
	
	@GetMapping("/register")
//	@PreAuthorize("isAuthenticated()")
	public void register(Integer category, Integer parentNo, Model model) { // parentNo -> Integer
		//bno -> ?
		log.warn(parentNo);
		model.addAttribute("board", parentNo != null ? service.get(parentNo) : null);
		model.addAttribute("category", category);
	}
	
	@PostMapping("/register")
	public String register(BoardDomain board, RedirectAttributes rttr) {
		log.warn("register : " + board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list?category=" +board.getCategory();
		//return null;
	}
	
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
		model.addAttribute("cri",cri);
	}
	
	
	@PostMapping("/modify")
	public String modify(BoardDomain board, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri) {
		log.info("modify : " + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "sucess");
		}
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("remove : " + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "sucess");
		}
		return "redirect:/board/list" + cri.getListLink();
	}
	
	
}