package com.myproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.domain.AuthDomain;
import com.myproject.domain.MemberDTO;
import com.myproject.domain.MemberDomain;
import com.myproject.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member/*")
@Log4j2
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/test")
	public void test() {
		
	}
	
	@GetMapping("login")
	public String loginInput(@SessionAttribute(name = "member", required = false) MemberDomain memberDomain){
		if(memberDomain != null) {
			return "/index";
		}
		
		return "/member/login";
	}
	
	@PostMapping("login")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpServletRequest httpServletRequest, BindingResult bindingResult, Model model){
		
		MemberDomain memberDomain = memberService.login(memberDTO);
		
		if(memberDomain == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 틀렸습니다.");
            log.info(bindingResult);
        }

        if(bindingResult.hasErrors()) {
        	log.info(bindingResult);
        	model.addAttribute("bindingResult", bindingResult);
            return "member/login";
        }
        
		MemberDomain selectedMember = memberService.findByUserNo(memberDomain.getUserNo());
		List<AuthDomain> memberAuthList = selectedMember.getAuthList();
		String memberAuth = memberAuthList.get(0).getAuth();
        
		httpServletRequest.getSession().invalidate();
        HttpSession session = httpServletRequest.getSession(true);
		
		session.setAttribute("member", memberDomain);
		session.setAttribute("memberId", memberDomain.getUserId());
		session.setAttribute("memberName", memberDomain.getUserName());
		session.setAttribute("memberNo", memberDomain.getUserNo());
		session.setAttribute("memberAuth", memberAuth);
		
		log.info(memberAuth);
		
		return "redirect:/index";
	}
	
	@PostMapping("logout")
	public String logout(MemberDomain memberDomain, HttpServletRequest httpServletRequest) {
		
		httpServletRequest.getSession().invalidate();
		
		return "redirect:/index";
	}
	
	@GetMapping("terms")
	public void getTerms(){
		log.info("약관동의 페이지");
	}
	
	@GetMapping("/join")
	public String join(@SessionAttribute(name = "memberId", required = false) String userId) {
		if(userId != null) {
			return "redirect:/index";
		}
		
		return "member/join";
	}
	
	@PostMapping("/join")
	public String join(MemberDomain memberDomain, RedirectAttributes rttr) {
		log.info("================================");
		log.info("register: " + memberDomain);
		memberService.join(memberDomain);
		rttr.addFlashAttribute("result", memberDomain.getUserId());
		return "redirect:/member/login";
	}
	
	@RequestMapping("/idcheck")
	@ResponseBody
	public Map<Object, Object> idcheck(@RequestBody MemberDomain memberDomain) {
		log.info(memberDomain); 
		int count = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();
		 
		count = memberService.idChk(memberDomain);
		map.put("cnt", count);
		 
		return map;
	}
	
	
	@GetMapping("pwCheck") 
	public String getPwCheck(@SessionAttribute(name = "memberId", required = false) String userId) {
		
		if(userId == null) {
			return "redirect:/member/login";
		}

		log.info("비밀번호확인......");
		
		return "member/pwCheck";
	}
	
	@PostMapping("withdraw")
	public String withdraw(@SessionAttribute(name = "memberNo", required = false) int userNo, String UserPw, HttpSession session) {
		
		log.warn("비밀번호..." + UserPw);
		log.warn("userNo : " + userNo);
		
		MemberDomain memberDomain = memberService.findByUserNo(userNo);
		
		if(memberDomain.getUserPw() == UserPw) {
			log.warn("비밀번호 불일치");
			return "redirect:/index";
		}
		log.warn("비밀번호 일치 : " + userNo);
		log.warn("회원탈퇴");
		memberService.withdraw(userNo);
		session.invalidate();
		return "redirect:/index";
	}
	
	
	
	
	
	
	
}
