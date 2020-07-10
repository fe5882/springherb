package com.will.herb.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.member.model.MemberService;
import com.will.herb.member.model.MemberVO;

@Controller
@RequestMapping("/login/login.do")
public class LoginController {
	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login_get() {
		logger.info("로그인 창");
		
		return "login/login";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String login_post(@RequestParam String userid, @RequestParam(required = false) String saveId,
			@RequestParam String pwd, HttpServletRequest request, HttpServletResponse response, 
			Model model) {
		logger.info("로그인 처리 파라미터 userid= {}, pwd={}", userid, pwd);
		logger.info("saveId={}", saveId);
		
		int result = 0;
		
		result = memberService.checkLogin(userid, pwd);
		logger.info("로그인 처리결과 result={}", result);
		
		
		String msg = "로그인 처리 실패", url = "/login/login.do";
		if(result == memberService.LOGIN_OK) {
			//쿠키처리
			if("on".equals(saveId)) {
				Cookie ck1 = new Cookie("userid", userid);
				
				ck1.setMaxAge(60*60*24*7);	//일주일
				
				response.addCookie(ck1);
				
			}else {
				Cookie[] cookies = request.getCookies();           

				for(int i = 0 ; i<cookies.length; i++){                     
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);       

				}

			}
			//세션처리
			MemberVO vo = memberService.selectMember(userid);
			request.getSession().setAttribute("userid", userid);
			request.getSession().setAttribute("name", vo.getName());
			
			
			msg = userid + "님 로그인 되었습니다.";
			url = "/index.do";
			
			
		}else if(result == memberService.PWD_DISAGREE) {
			msg = "비밀번호 불일치";
		}else if(result == memberService.ID_NONE) {
			msg = "아이디가 존재하지 않습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
}
