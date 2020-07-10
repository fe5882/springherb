package com.will.herb.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.member.model.MemberService;
import com.will.herb.member.model.MemberVO;


@Controller
@RequestMapping("/member")
public class MemberController {
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping("/agreement.do")
	public String agreement() {
		logger.info("회원약관 페이지 보여주기");
		
		return "member/agreement";
	}
	
	//매핑이랑 뷰페이지 이름이랑 같으면 void로 해도 간다
	@RequestMapping("/register.do")
	public String register() {
		logger.info("회원가입 페이지 보여주기");
		
		return "member/register";
	}
	
	@RequestMapping(value="/checkUserid.do")
	public String checkUserid(@RequestParam String userid, Model model) {
		logger.info("아이디 중복확인, 파라미터 userid={}", userid);
		
		int result = 0;
		if(userid != null && !userid.isEmpty()) {
			result = memberService.duplicateId(userid);
		}
		logger.info("아이디 중복확인 결과, result = {}", result);
		model.addAttribute("result", result);
		model.addAttribute("EXIST_ID", memberService.EXIST_ID);
		model.addAttribute("NON_EXIST_ID", memberService.NON_EXIST_ID);
		
		return "member/checkUserid";
	}
	
	@RequestMapping("/memberWrite.do")
	public String insertMember(@ModelAttribute MemberVO vo, 
			@RequestParam String email3,
			Model model) {
		logger.info("회원가입 처리 파라미터 vo={}", vo);
		
		
		
		if(vo.getEmail1() == null || vo.getEmail1().isEmpty()) {
	         vo.setEmail2("");
	      }else {
	         if(vo.getEmail2().equals("etc")) {
	            vo.setEmail2(email3);
	         }
	      }
		
		if(vo.getHp2() == null || vo.getHp2().isEmpty() || vo.getHp3() == null || vo.getHp3().isEmpty()) {
			vo.setHp1("");
			vo.setHp2("");
			vo.setHp3("");
		}
		
		int cnt = memberService.insertMember(vo);
		String msg = "회원가입 실패", url = "member/register";
		if(cnt > 0) {
			msg = "회원가입 성공";
			url = "login/login";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
}
