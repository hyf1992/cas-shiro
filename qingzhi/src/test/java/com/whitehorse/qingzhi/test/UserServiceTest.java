package com.whitehorse.qingzhi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.whitehorse.qingzhi.service.UserService;


/**
* @author hyf
* @date 2017年4月5日
* @description 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-hibernate.xml")
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@Test
	public void testFindUserByName(){
		
		
	}
}
