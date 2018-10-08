package com.air.security.sodb.support.authentication.config;

import java.security.GeneralSecurityException;

import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.HandlerResult;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;

public class UsernamePasswordSystemAuthenticationHandler  extends AbstractPreAndPostProcessingAuthenticationHandler{

	
	public UsernamePasswordSystemAuthenticationHandler(String name, ServicesManager servicesManager,
			PrincipalFactory principalFactory, Integer order) {
		super(name, servicesManager, principalFactory, order);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean supports(Credential arg0) {
		// TODO Auto-generated method stub
		//判断这个验证器，是否执行，那还用说，肯定让他支持啊
		return true;
	}

	@Override
	protected HandlerResult doAuthentication(Credential arg0) throws GeneralSecurityException, PreventedException {
		// TODO Auto-generated method stub
		return null;
	}

}
