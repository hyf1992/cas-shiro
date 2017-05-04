package com.whitehorse.qingzhi.web.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whitehorse.qingzhi.util.CasTicket;


@Controller
public class LoginController {

	@Autowired
	private CasRealm casRealm;
	
	@RequestMapping("/login")
    public @ResponseBody String login(String username,String password,HttpServletRequest request, HttpServletResponse response) throws IOException {
		String server = casRealm.getCasServerUrlPrefix()+"/login";
		String service = casRealm.getCasService();
		if("admin".equals(username) && "123456".equals(password)){
			
			
			Map<String, String> params  = new HashMap<String, String>();
			params.put("username", "admin");
			params.put("password", "123456");
			params.put("service", service);
			params.put("auto", "true");
			WebUtils.issueRedirect(request, response, server,params);
			
			return "成功 ticket:";
			
		}else{
			WebUtils.issueRedirect(request, response, server+"?service="+service);
			return "失败";
		}
    	
       
    }
    @RequestMapping("/success")
    public @ResponseBody String success() {
    	
        return "success";
    }
    @RequestMapping("/cas")
    public @ResponseBody String failed() {
    	
        return "cas";
    }
    @RequestMapping("/fail")
    public @ResponseBody String cas() {
    	
        return "fail";
    }
}
