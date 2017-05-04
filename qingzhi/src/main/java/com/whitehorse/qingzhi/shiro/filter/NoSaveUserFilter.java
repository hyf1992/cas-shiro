package com.whitehorse.qingzhi.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;

/**
* @author hyf
* @date 2017年4月11日
* @description 
*/
public class NoSaveUserFilter extends UserFilter{

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		redirectToLogin(request, response);
		return false;
	}
}
