package com.will.herb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	public static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	
	@RequestMapping("/index.do")
	public String index() {
		logger.info("메인 화면입니다.");
		
		return "index";
	}
}
