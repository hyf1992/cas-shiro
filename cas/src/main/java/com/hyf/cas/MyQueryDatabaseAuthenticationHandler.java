package com.hyf.cas;

import org.jasig.cas.authentication.Credential;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.adaptors.jdbc.QueryDatabaseAuthenticationHandler;

/**
* @author hyf
* @date 2017年5月2日
* @description 
*/
public class MyQueryDatabaseAuthenticationHandler extends QueryDatabaseAuthenticationHandler {
	@Override
	protected boolean preAuthenticate(Credential credential) {
		
		System.out.println("preAuthenticate");
		return super.preAuthenticate(credential);
	}
	@Override
	protected HandlerResult postAuthenticate(Credential credential, HandlerResult result) {
		System.out.println("postAuthenticate");
		return super.postAuthenticate(credential, result);
	}
}
