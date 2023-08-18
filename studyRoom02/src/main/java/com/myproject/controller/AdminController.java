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

import com.myproject.domain.Criteria;
import com.myproject.domain.FeeDomain;
import com.myproject.domain.MemberDomain;
import com.myproject.domain.PageDTO;
import com.myproject.domain.RegInfoDomain;
import com.myproject.service.AdminService;
import com.myproject.service.BoardService;
import com.myproject.service.PaymentService;
import com.myproject.service.RegInfoService;
import com.myproject.service.SeatService;

import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
@RequestMapping("/admin/*")
//@AllArgsConstructor
public class AdminController {

	@Autowired
	private AdminService AdminService;
	@Autowired
	private SeatService seatService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private RegInfoService regInfoService;
	@Autowired
	private PaymentService paymentService;
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("admin")
	public void getAdmin(Criteria cri, Model model){
		log.warn("관리자 페이지");
		
		model.addAttribute("memberCount", AdminService.getMemberTotal(cri));
		model.addAttribute("usingCount", AdminService.getMemberContactTotalCount(cri));
		model.addAttribute("totalIncome", AdminService.getTotalImcome());
		
		model.addAttribute("pageMaker", new PageDTO(boardService.getTotalCount(cri), cri));
		model.addAttribute("notiList", AdminService.getNotiList());
		model.addAttribute("QAList", AdminService.getQAList());
		
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("admin_seat")
	public void getSeat(Criteria cri, Model model) {
		log.warn("관리자 좌석 관리 페이지..");
		model.addAttribute("UnregList", AdminService.getUnregListTest(cri)); // 미등록 회원 목록
		model.addAttribute("cri", cri);
		model.addAttribute("pageMaker", new PageDTO(AdminService.getMemberTotal(cri), cri));
		model.addAttribute("etSeat", seatService.getEmptySeatList()); // 빈 좌석 목록
		model.addAttribute("planInfo",AdminService.showSeatPlanInfo()); // 배치도 정보
	}


//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("admin_seat")
	public String postSeat(RegInfoDomain regInfoDomain, Model model) {
		log.warn("전송된 regInfoDomain:: " + regInfoDomain);
		FeeDomain feeVO = paymentService.findBy(regInfoDomain.getFno());
		model.addAttribute("feeVO", feeVO);
		log.info("feeVO : " + feeVO);
		regInfoService.RegisterRegInfo(regInfoDomain);
		return "redirect:/admin/admin_seat";
	}
	
	
	
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("memberList")
	public void getMemberList(Criteria cri, Model model){
		log.warn("회원목록" + cri + "페이지");		
		model.addAttribute("list",AdminService.memberList(cri));
		model.addAttribute("cri", cri);
		model.addAttribute("pageMaker", new PageDTO(AdminService.getMemberTotal(cri), cri));
	}
	
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("memberDetail")
	public void getMemberDetail(@RequestParam int userNo, @ModelAttribute("cri") Criteria cri, Model model){
		log.warn("회원상세정보 페이지");
		model.addAttribute("member", AdminService.memberDetail(userNo));
		model.addAttribute("address", AdminService.memberAddr(userNo));
		model.addAttribute("cri", cri);
	}
	
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("memberContact")
	public void getMemberContact(Criteria cri, Model model){
		log.warn("회원연락" + cri + "페이지");		
		model.addAttribute("list",AdminService.memberContactList(cri));
		model.addAttribute("cri", cri);
		model.addAttribute("pageMaker", new PageDTO(AdminService.getMemberContactTotalCount(cri), cri));
	}
	
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("paymentList")
	public void getPaymentList(Criteria cri, Model model){
		log.warn("결제내역 페이지");	
		log.warn(cri.getStartDate());
		log.warn(cri.getEndDate());
		log.warn(AdminService.paymentList(cri));
		model.addAttribute("list",AdminService.paymentList(cri));
		model.addAttribute("cri", cri);
		model.addAttribute("pageMaker", new PageDTO(AdminService.getPaymentTotalCount(cri), cri));
	}
	
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("regProperties")
	public void getRegProperties(Criteria cri, Model model){
		log.warn("등록정보 페이지");					
		log.warn(cri.getStartDate());
		log.warn(cri.getEndDate());
		log.warn(AdminService.regProperties(cri));
		model.addAttribute("list",AdminService.regProperties(cri));
		model.addAttribute("cri", cri);
		model.addAttribute("pageMaker", new PageDTO(AdminService.getRegTotalCount(cri), cri));
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("regDetail")
	public void getRegDetail(@RequestParam int regNo, @ModelAttribute("cri") Criteria cri, Model model){
		log.warn("등록 상세정보 페이지");
		model.addAttribute("regPP", AdminService.regDetail(regNo));
		model.addAttribute("emptySeatList", AdminService.getEmptySeatList());
		model.addAttribute("cri", cri);
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("updateMember")
	public String updateMember(MemberDomain memberDomain, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.warn("회원 정보를 수정합니다...");
		log.warn(memberDomain);
		AdminService.updateMember(memberDomain);
		return "redirect:/admin/memberList" + cri.getListLink();
	}
	
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("updateRegPP")
	public String updateRegPP(RegInfoDomain regInfoVO, RedirectAttributes rttr, @ModelAttribute("cri") Criteria cri){
		log.warn("등록 상태를 수정합니다...");
		log.warn(regInfoVO);
		AdminService.updateRegPP(regInfoVO);
		return "redirect:/admin/regProperties" + cri.getListLink();
	}
	
	@PostMapping("revoke")
	public String revokeReg(int userNo) {
		log.warn("회원 번호:: " + userNo);
		AdminService.revokeTheRegistration(userNo);
		return "redirect:/admin/admin_seat";
	}

	
}
