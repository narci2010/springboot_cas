package com.lebron.controller;

import java.security.Principal;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {
	
	@RequestMapping("/user")
	public String showUser(Model model, HttpServletRequest request) {
		
		//Object object =request.getSession().getAttribute("_const_cas_assertion_");
		//Assertion assertion =(Assertion)object;
		
		//获取cas给我们传递回来的对象，这个东西放到了session中
		//session的 key是 _const_cas_assertion_
		Assertion assertion = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);  
		
		//获取登录用户名
		String loginName =assertion.getPrincipal().getName();
    	System.out.printf("登录用户名:%s\r\n",loginName);
    	
    	//获取自定义返回值的数据
		Principal principal  = (AttributePrincipal) request.getUserPrincipal();
		if (request.getUserPrincipal() != null) {
			
			if (principal instanceof AttributePrincipal) {
				//cas传递过来的数据
				Map<String,Object> result =( (AttributePrincipal)principal).getAttributes();
				for(Map.Entry<String, Object> entry :result.entrySet()) {
					String key = entry.getKey();
					Object val = entry.getValue();
					System.out.printf("%s:%s\r\n",key,val);
				}
			}
		}
		
		model.addAttribute("uname", request.getRemoteUser());
		return "userinfo";
	}
	
	private static void printMap(Map<String, Object> map) {
		for (java.util.Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.printf("%s\t%s\t%s\n", entry.getKey(), entry.getValue().getClass(), entry.getValue());
		}
	}
	
	private static void printEnums(Enumeration<String> enums) {
		while(enums.hasMoreElements()) {
			System.out.println(enums.nextElement());
		}
	}

}
