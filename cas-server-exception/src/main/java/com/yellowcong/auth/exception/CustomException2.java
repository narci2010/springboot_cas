package com.yellowcong.auth.exception;

import javax.security.auth.login.AccountExpiredException;

public class CustomException2 extends AccountExpiredException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomException2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomException2(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
