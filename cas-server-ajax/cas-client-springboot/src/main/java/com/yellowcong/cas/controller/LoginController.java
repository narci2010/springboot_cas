package com.yellowcong.cas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class LoginController {
	/**
	 * 创建日期:2018年2月3日<br/>
	 * 创建时间:下午5:32:41<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:单点登出
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/loginOut1")
	public String loginOut(HttpSession session) {
		session.invalidate();
		// http://yellowcong.com:8080/cas-client-maven/user/loginOut/success

		// 这个是直接退出，走的是默认退出方式
		return "redirect:https://yellowcong.com:9000/logout";
	}

	@RequestMapping("/loginOut2")
	public String loginOut2(HttpSession session) {
		session.invalidate();
		// 退出登录后，跳转到退成成功的页面，不走默认页面
		return "redirect:https://yellowcong.com:9000/logout?service=http://yellowcong.com:8888/user/loginOut/success";
	}
	
	@RequestMapping("/loginOut/success")
	@ResponseBody
	public String loginOut2() {
		return "注销成功";
	}

}
