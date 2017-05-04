package com.whitehorse.qingzhi.shiro.interceptor;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;

/**
* @author hyf
* @date 2017年4月12日
* @description 
*/
public class MyAuthorizationAttributeSourceAdvisor extends AuthorizationAttributeSourceAdvisor{

	public MyAuthorizationAttributeSourceAdvisor() {
        setAdvice(new MyAopMethodInterceptor());
    }
}
