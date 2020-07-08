package com.will.herb.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.board.model.BoardService;
import com.will.herb.board.model.BoardVO;

@Controller
@RequestMapping("/board/edit.do") 
public class EditController {
	private Logger logger = LoggerFactory.getLogger(EditController.class);
	@Autowired
	private BoardService boardService;

	@RequestMapping(method = RequestMethod.GET)
	public String edit_get(@RequestParam(defaultValue = "0") int no, Model model) {

		logger.info("수정화면, 파라미터 no={}", no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/board/list.do");

			return "common/message";
		}

		BoardVO vo = boardService.selectByNo(no);

		logger.info("수정화면 조회결과 , 파라미터 no={}", no);

		model.addAttribute("vo", vo);

		return "board/edit";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String edit_post(@ModelAttribute BoardVO vo, Model model) {
		logger.info("수정처리화면, 파라미터 vo={}", vo);

		if(!boardService.checkPwd(vo.getNo(), vo.getPwd())){
			model.addAttribute("msg", "비밀번호가 일치하지않습니다.");
			model.addAttribute("url", "/board/edit.do?no="+vo.getNo());

			return "common/message";
		}
		int cnt = boardService.updateBoard(vo);

		String url = "/board/edit.do?no="+vo.getNo(), msg = "수정 실패";
		if(cnt > 0) {
			url = "/board/detail.do?no="+vo.getNo(); 
			msg = "수정 성공";
		}

		model.addAttribute("url", url);
		model.addAttribute("msg", msg);


		return "common/message";
	}
}
