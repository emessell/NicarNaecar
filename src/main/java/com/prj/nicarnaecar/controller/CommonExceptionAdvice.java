package com.prj.nicarnaecar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice	// 현재 클래스가 '컨트롤러에서 발생하는 Exception을 전반적으로 처리하는 클래스'임을 알려줌
public class CommonExceptionAdvice {

	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	@ExceptionHandler(Exception.class)
	private ModelAndView err(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("err_exception");
		mav.addObject("exception",e);
		return mav;
	}
}
