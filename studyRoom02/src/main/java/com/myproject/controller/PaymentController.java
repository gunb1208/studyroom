package com.myproject.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myproject.domain.FeeDomain;
import com.myproject.domain.MemberDomain;
import com.myproject.domain.PaymentDomain;
import com.myproject.domain.RegInfoDomain;
import com.myproject.service.PaymentService;
import com.myproject.service.RegInfoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/payment/*")
@AllArgsConstructor
public class PaymentController {

	@Autowired
	private PaymentService paymentservice;
	@Autowired
	private RegInfoService reginfoservice;
	
	@GetMapping("payment")
	public void getPayment() {
		log.info("payment..");
	}
	
	@PostMapping("payment")
//	@PreAuthorize("principal.vo.userNo == #regInfoDomain.userNo")
	public void postPayment(RegInfoDomain regInfoDomain, Integer fno2, Model model) {
		
		log.info("payment..." + regInfoDomain);
		
		FeeDomain feeDomain = paymentservice.findBy(regInfoDomain.getFno());
		model.addAttribute("feeDomain", feeDomain);
		log.info("feeDomain : " + feeDomain);
		
		if(fno2 != null) {
			FeeDomain feeDomain2 = paymentservice.findBy(fno2);
			model.addAttribute("feeDomain2", feeDomain2);
			log.info("feeDomain2 : " + feeDomain2);
			}
		
		reginfoservice.RegisterTmpReg(regInfoDomain);
	}
	
	@PostMapping("payComplete")
//	@PreAuthorize("principal.vo.userNo == #regInfoDomain.userNo")
	public void payComplete(PaymentDomain paymentDomain, RegInfoDomain regInfoDomain, Model model) {
		
		log.info("payment......");
		log.info("reginfoVO" + regInfoDomain);
		log.info("paymentDomain" + paymentDomain);
		
		int regNo = paymentservice.pay(paymentDomain, regInfoDomain);
		model.addAttribute("regNo", regNo);
		
		log.info("regNo..." + regNo);
	}
	
	@GetMapping("payComplete")
	public void getPayComplete() {
		
	}
	
	@GetMapping("expayComplete")
	public void getExpayComplete() {
		
	}
	
	@PostMapping("expayComplete")
//	@PreAuthorize("principal.vo.userNo == #regInfoDomain.userNo")
	public void payExComplete(PaymentDomain paymentDomain, RegInfoDomain regInfoDomain, Model model) {
		
		log.info("payment......");
		log.info("reginfoVO" + regInfoDomain);
		log.info("paymentDomain" + paymentDomain);
		
		paymentservice.extendPayment(paymentDomain, regInfoDomain);
		
//		model.addAttribute("regNo", regNo);
//		log.info("regNo..." + regNo);
	}
	
	@GetMapping("payTest")
	public void payTest() {
		log.info("payTest..");
	}
	
	
	@GetMapping("exPayment")
	public void getExPayment(Model model, HttpServletRequest request) {
		log.info("기간 연장 결제 페이지..");
		/* 만료일의 날짜 포맷을 변경하는 작업*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		/* 이용자의 만료일을 처리하는 작업 */
		HttpSession session = request.getSession();
		MemberDomain user = (MemberDomain) session.getAttribute("member");
//		CustomUser user = (CustomUser) auth.getPrincipal(); // 로그인 된 이용자의 정보를 가져 온다.
		
		RegInfoDomain regInfoDomain = reginfoservice.read(user.getUserNo()); // 해당 이용자의 이용정보를 불러온다.
		String endDate = sdf.format(regInfoDomain.getEndDate());
		FeeDomain feeDomain = paymentservice.findBy(regInfoDomain.getFno());
		
		model.addAttribute("regInfoDomain", regInfoDomain);
		model.addAttribute("endDate",endDate);
		model.addAttribute("feeDomain", feeDomain);
	}
	/*
	 * 연장결제완료페이지이동컨트롤러
	 */
//	@PreAuthorize("principal.vo.userNo == #regInfoDomain.userNo")
	@PostMapping("exPayComplete")
	public void postExPayComplete(RegInfoDomain regInfoDomain, PaymentDomain paymentDomain, Model model) {
		
		log.info("exPayment......");
		log.info("regInfoDomain" + regInfoDomain);
		log.info("paymentDomain" + paymentDomain);
		
		model.addAttribute("PaymentDomain", paymentDomain);
		model.addAttribute("RegInfoDomain", regInfoDomain);
		
		/*연장실행 - 결제 및 기간연장*/
		paymentservice.extendPayment(paymentDomain, regInfoDomain);
	}
	/*
	 * fno로 요금제테이블의 정보를 가져오기 위한 페이지
	 */
	@ResponseBody
	@GetMapping("getFee")
	public FeeDomain getFee(Integer fno) {
		
		log.info("getFee......" + fno);
		return paymentservice.findBy(fno);
		
	}
	
	
}
