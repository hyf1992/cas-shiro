package com.whitehorse.qingzhi.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
* @author hyf
* @date 2017年4月11日
* @description 
*/
public class ServerFormAuthenticationFilter extends FormAuthenticationFilter {
	private JSONObject jb = null;
	private Integer ip = null;
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginSubmission(request, response)) {
			return executeLogin(request, response);
		}
		return false;
	}
	
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		String fallbackUrl = (String) getSubject(request, response).getSession().getAttribute("authc.fallbackUrl");
		
		if (StringUtils.isEmpty(fallbackUrl)) {
			fallbackUrl = getSuccessUrl();
		}
		WebUtils.redirectToSavedRequest(request, response, fallbackUrl);
		
		Session session = SecurityUtils.getSubject().getSession();
		
		session.setAttribute("ip", ip);
		
	}
	/*@Override
	protected String getUsername(ServletRequest request) {
		jb = getParam(request);
		if(jb!=null){
			ip = jb.getInteger("ip");
			return jb.getString(getUsernameParam());
		}
		return null;
	}

	@Override
	protected String getPassword(ServletRequest request) {
		if(jb!=null){
			return jb.getString(getPasswordParam());
		}
		return null;
	}
*/
	/**
	 * 获取body中的json字符串参数
	 * @param request
	 * @return
	 */
	public JSONObject getParam(ServletRequest request) {
		JSONObject jb = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			String paramJson = sb.toString();
			if(!StringUtils.isEmpty(paramJson)){
				jb = JSON.parseObject(paramJson);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return jb;
	}
}
