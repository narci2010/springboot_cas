/*package com.yellowcong.auth.action;

import javax.servlet.http.HttpServletRequest;

import org.apereo.cas.authentication.Credential;
import org.apereo.cas.web.support.WebUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.yellowcong.auth.constants.Constants;

*//**
  * 创建日期:2018/02/07<br/>
  * 创建时间:9:05:09<br/>
  * 创建用户:yellowcong<br/>
  * 机能概要:
  *//*
public class ValidateLoginCaptchaAction extends AbstractAction {
	
	private static final String CODE = "captchaError";
	
	@Override
	protected Event doExecute(RequestContext context) throws Exception {
		// TODO Auto-generated method stub
		
		//获取登录传递过来的表单信息
		Credential credential = WebUtils.getCredential(context);
		
		HttpServletRequest request = WebUtils.getHttpServletRequestFromExternalWebflowContext(context);
		//客户端发送过来的校验码
		String inCode = request.getParameter(Constants.STORE_CODE);
		
		//校验码失败跳转到登录页 
		//用于获取session中存储的code
		String webCode = request.getSession().getAttribute(Constants.STORE_CODE).toString();
		if(webCode == null || !webCode.equals(inCode)) {
			return this.getError(context);
		}
		return null;
	}
	
	*//**
	 * 跳转到错误页
	 * 
	 * @param requestContext
	 * @return
	 *//*
	private Event getError(final RequestContext requestContext) {
		final MessageContext messageContext = requestContext.getMessageContext();
		messageContext.addMessage(new MessageBuilder().error().code(CODE).build());
		return getEventFactorySupport().event(this, CODE);
	}

}
*/