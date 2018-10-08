package com.yellowcong.auth.conf;

import org.apereo.cas.configuration.CasConfigurationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yellowcong.auth.controller.CaptchaController;

/**
 * 创建日期:2018/02/07<br/>
 * 创建时间:8:38:31<br/>
 * 创建用户:yellowcong<br/>
 * 机能概要:自定义控制器
 */
@Configuration("captchaConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CaptchaConfiguration {

	// 注册bean到spring容器
	@Bean
	@ConditionalOnMissingBean(name = "captchaController")
	public CaptchaController captchaController() {
		return new CaptchaController();
	}
}
