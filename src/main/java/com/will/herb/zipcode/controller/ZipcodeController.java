package com.will.herb.zipcode.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.zipcode.model.ZipcodeService;
import com.will.herb.zipcode.model.ZipcodeVO;

@Controller
public class ZipcodeController {
	public static final Logger logger = LoggerFactory.getLogger(ZipcodeController.class);
	
	@Autowired
	private ZipcodeService zipcodeService;
	
	@RequestMapping("/zipcode/zipcode.do")
	public void zipcode(@RequestParam(required = false) String dong, Model model) {
		logger.info("우편번호 검색페이지 파라미터 dong={}", dong);
		List<ZipcodeVO> list = null;
		if(dong != null && !dong.isEmpty()) {
			list = zipcodeService.selectByDong(dong);
		}
		model.addAttribute("list", list);
		
		
	}
}
