package com.hyf.cas;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.jasig.cas.authentication.handler.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
* @author hyf
* @date 2017年5月2日
* @description 
*/
@Component("shiroPasswordEncoder")
public final class ShiroPasswordEncoder implements PasswordEncoder{
	
	public static final String salt= "00ce0cdd-15a2-4659-bbfd-22aae4f11c40";

	private final String hashAlgorithmName;
	private final Integer hashIterations;
	
	
	
	@Autowired
    public ShiroPasswordEncoder(@Value("${cas.authn.password.encoding.alg:}") final String hashAlgorithmName,@Value("${cas.authn.password.encoding.ite:}") final Integer hashIterations) {
		this.hashAlgorithmName = hashAlgorithmName ==null?"md5":hashAlgorithmName;
        this.hashIterations = hashIterations==null?2:hashIterations;
    }

	@Override
	public String encode(final String password) {
		String base64Encoded = Base64.encodeToString(salt.getBytes());
    	String newPassWord = new SimpleHash(hashAlgorithmName, password,  ByteSource.Util.bytes(base64Encoded),hashIterations).toHex();
		
		return newPassWord;
	}
}
