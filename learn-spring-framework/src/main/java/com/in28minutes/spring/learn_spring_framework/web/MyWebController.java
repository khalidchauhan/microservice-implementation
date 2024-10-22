package com.in28minutes.spring.learn_spring_framework.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.in28minutes.spring.learn_spring_framework.business.MyBusinessService;

@Controller
public class MyWebController {

	@Autowired
	private MyBusinessService service;
	
	public MyWebController(MyBusinessService service) {
		this.service = service;
	}

	public long returnValueFromBusinessService() {
		return service.calculateSum();
	}
}
