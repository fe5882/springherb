package com.will.herb.board.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.will.herb.board.model.BoardService;
import com.will.herb.board.model.BoardVO;
import com.will.herb.common.PaginationInfo;
import com.will.herb.common.SearchVO;
import com.will.herb.common.Utility;

@Controller
public class ListController {
	private static final Logger logger = LoggerFactory.getLogger(ListController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/list.do")
	public String list(@ModelAttribute SearchVO searchVo, Model model) {
		//1.
		logger.info("글 목록 파라미터 searchVo={}", searchVo);
		
		//[1] PaginationInfo 생성
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(Utility.BLOCKSIZE);
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		
		//[2] SearchVo에 값 세팅
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		searchVo.setRecordCountPerPage(Utility.RECORD_COUNT);
		//2.
		List<BoardVO> list = boardService.selectAll(searchVo);
		logger.info("글목록 결과, list.size=" + list.size());
		
		int totalRecord = boardService.selectTotalRecord(searchVo);
		logger.info("글목록, 전체 레코드 개수 : " + totalRecord);
		
		pagingInfo.setTotalRecord(totalRecord);
		
		//3.
		
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
		return "board/list";
	}
}
