package com.yellowcong.auth.handler;

import java.security.GeneralSecurityException;
import java.util.Collections;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.FailedLoginException;

import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.HandlerResult;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.exceptions.AccountDisabledException;
import org.apereo.cas.authentication.exceptions.InvalidLoginLocationException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;

import com.yellowcong.auth.exception.CustomException1;
import com.yellowcong.auth.exception.CustomException2;
/**
 * @author yellowcong
 * 创建日期:2018/02/02
 *
 */
public class CustomerHandler extends AbstractPreAndPostProcessingAuthenticationHandler {

	public CustomerHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory,
			Integer order) {
		super(name, servicesManager, principalFactory, order);
	}

	/**
	 * 用于判断用户的Credential(换而言之，就是登录信息)，是否是俺能处理的
	 * 就是有可能是，子站点的登录信息中不止有用户名密码等信息，还有部门信息的情况
	 */
	@Override
	public boolean supports(Credential credential) {
		//判断传递过来的Credential 是否是自己能处理的类型
		return credential instanceof UsernamePasswordCredential;
	}
	
	/**
	 * 用于授权处理
	 */
	@Override
	protected HandlerResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
		UsernamePasswordCredential usernamePasswordCredentia = (UsernamePasswordCredential) credential;
		
		//获取传递过来的用户名和密码
		String username = usernamePasswordCredentia.getUsername();
        String password = usernamePasswordCredentia.getPassword();
    	
        if(username.equals("doubi1")){
        	//doubi1 的时候，抛出 异常信息1
    		//异常信息1
    		throw new CustomException1();
    	}else if(username.equals("doubi2")){
        	//doubi1 的时候，抛出 异常信息1
    		//异常信息1
    		throw new CustomException2();
    	}else if(username.startsWith("admin")) {
    		//当是admin用户的情况，直接就登录了，谁叫他是admin用户呢
    		//直接返回去了
    		return createHandlerResult(credential, this.principalFactory.createPrincipal(username, Collections.emptyMap()), null);
    	}else if (username.startsWith("lock")) {
            //用户锁定
            throw new AccountLockedException();
        } else if (username.startsWith("disable")) {
            //用户禁用
            throw new AccountDisabledException();
        } else if (username.startsWith("invali")) {
            //禁止登录该工作站登录
            throw new InvalidLoginLocationException();
        } else if (username.startsWith("passorwd")) {
            //密码错误
            throw new FailedLoginException();
        } else if (username.startsWith("account")) {
            //账号错误
            throw new AccountLockedException();
        }
		return null;
	}

}
