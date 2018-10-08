package com.air.security.sodb.support.authentication.handler;

import javax.security.auth.login.LoginException;

public class DoubiException extends LoginException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DoubiException() {
		super();
	}

	public DoubiException(String msg) {
		super(msg);
	}
}
