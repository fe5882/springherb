package com.will.herb.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.will.herb.board.model.BoardService;
import com.will.herb.board.model.BoardVO;

@Controller
public class WriteController {
	private static final Logger logger = LoggerFactory.getLogger(WriteController.class);
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/board/write.do", method = RequestMethod.GET)
	public String write_get() {
		logger.info("글쓰기 화면");
		
		return "board/write";
		
	}
	
	@RequestMapping(value = "/board/write.do", method = RequestMethod.POST)
	public String write_post(@ModelAttribute BoardVO boardVo, Model model) {
		//1
		logger.info("글 등록, 파라미터 vo ={}", boardVo);
		//2.
		int cnt = boardService.insertBoard(boardVo);
		logger.info("글 등록 결과, cnt ={}, cnt");
		String msg = "", url="/board/write.do";
		if(cnt > 0) {
			msg = "글 등록되었습니다.";
			url = "/board/list.do";
		}
		//3.
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		//4.
		return "common/message";
		
		
	}
	
}
