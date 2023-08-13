package com.myproject.controller;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.domain.RegInfoDomain;
import com.myproject.domain.SeatDomain;
import com.myproject.service.RegInfoService;
import com.myproject.service.SeatService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/reg/*")
@AllArgsConstructor
public class RegController {

	private SeatService seatService;
	private RegInfoService regInfoService;
	
	@GetMapping("seat")
	public void getSeat(Model model){
		log.info("좌석등록 페이지");
		model.addAttribute("seatList", seatService.getAllList());
		model.addAttribute("regList", regInfoService.getAllList());
	}
	
	
	@GetMapping("changeSeat")
	public void changeSeat(Model model) {
		log.info("좌석 변경 페이지..");
		model.addAttribute("seatList", seatService.getAllList());
		model.addAttribute("regList", regInfoService.getAllList());
	}
	
//	@PreAuthorize("principal.vo.userNo == #regInfoDomain.userNo")
//	@PostMapping("changeSeat")
//	public String changeSeatP(RegInfoDomain regInfoDomain, SeatDomain seatDomain, RedirectAttributes rttr) {
//		regInfoService.changeSeat(regInfoDomain, seatDomain);
//		rttr.addFlashAttribute("msg", "changeOK");
//		return "redirect:/reg/seat";
//	}
	
	
}
