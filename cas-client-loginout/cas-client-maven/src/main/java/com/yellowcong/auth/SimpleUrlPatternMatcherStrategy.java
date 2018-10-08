package com.yellowcong.auth;

import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;

/**
 * 创建日期:2018年2月3日<br/>
 * 创建时间:下午5:36:48<br/>
 * 创建者    :yellowcong<br/>
 * 机能概要:过滤掉一些不需要授权，登录的界面
 */
public class SimpleUrlPatternMatcherStrategy implements UrlPatternMatcherStrategy {

	/**
	 * 创建日期:2018年2月3日<br/>
	 * 创建时间:下午5:42:36<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要: 判断是否匹配这个字符串
	 * @param url 用户请求的连接
	 * @return true : 我就不拦截你了
	 * 		   false :必须得登录了
	 */
	@Override
	public boolean matches(String url) {
		//http://yellowcong.com:8080/cas-client-maven/user/loginOut/success
		//当含有loginout的字段，就可以不用登录了
		return url.contains("/loginOut/success");
	}

	/**
	 * 正则表达式的规则，这个地方可以是web传递过来的
	 */
	@Override
	public void setPattern(String pattern) {
		
	}

}
