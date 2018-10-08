package com.yellowcong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *创建日期:2018年2月3日 <br/>
 *创建用户:yellowcong <br/>
 *机能说明:用户控制器
 */
@RequestMapping("/user")
@Controller
public class UserController {
	
	/**
	 *创建日期:2018年2月3日 <br/>
	 *创建用户:yellowcong <br/>
	 *机能说明:用户登录
	 *@return
	 */
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
}
