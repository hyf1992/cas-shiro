package com.whitehorse.qingzhi.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.whitehorse.qingzhi.dao.UserDao;
import com.whitehorse.qingzhi.entity.UserBaseInfo;

import java.util.List;

/**
* @author hyf
* @date 2017年4月5日
* @description 
*/
@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<UserBaseInfo> findByUsername(String username) {
		DetachedCriteria criteria = DetachedCriteria.forClass(UserBaseInfo.class);
		criteria.add(Restrictions.eq("userNickname", username));
		
		return (List<UserBaseInfo>) hibernateTemplate.findByCriteria(criteria);
	}
	
}
