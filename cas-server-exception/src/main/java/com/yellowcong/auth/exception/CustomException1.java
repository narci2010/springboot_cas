package com.yellowcong.auth.exception;

import javax.security.auth.login.AccountExpiredException;

public class CustomException1 extends AccountExpiredException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomException1() {
		super();
	}

	public CustomException1(String msg) {
		super(msg);
	}
}
