package com.lebron.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public String toIndex() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String toLogin() {
		return "login";
	}

}
