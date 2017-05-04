package com.whitehorse.qingzhi.dao;


import java.util.List;

import com.whitehorse.qingzhi.entity.UserBaseInfo;


/**
* @author hyf
* @date 2017年4月5日
* @description 
*/
public interface UserDao {

	

	List<UserBaseInfo> findByUsername(String username);

}
