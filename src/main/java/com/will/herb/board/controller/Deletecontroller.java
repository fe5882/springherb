package com.will.herb.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.board.model.BoardService;
import com.will.herb.board.model.BoardVO;

@Controller
@RequestMapping(value = "/board/delete.do")
public class Deletecontroller {
	Logger logger = LoggerFactory.getLogger(Deletecontroller.class);
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String delete_get(@RequestParam(defaultValue = "0") int no, ModelMap model) {
		logger.info("삭제화면 파라미터 no={}", no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/board/detail.do?no="+no);
		}
		
		return "board/delete";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String delete_post(@ModelAttribute BoardVO vo, Model model) {
		if(!boardService.checkPwd(vo.getNo(), vo.getPwd())) {
			model.addAttribute("msg", "비밀번호가 틀립니다.");
			model.addAttribute("url", "/board/delete.do?no="+vo.getNo());
			
			return "common/message";
		}
		
		int cnt = boardService.deleteBoard(vo.getNo());
		String msg = "삭제 실패", url = "/board/delete.do?no="+vo.getNo();
		if(cnt > 0) {
			msg = "삭제 성공";
			url = "/board/list.do";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
		
	}
	
	
}
