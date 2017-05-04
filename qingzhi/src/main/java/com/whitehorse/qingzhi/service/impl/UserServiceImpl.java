package com.whitehorse.qingzhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whitehorse.qingzhi.dao.UserDao;
import com.whitehorse.qingzhi.service.PasswordHelper;
import com.whitehorse.qingzhi.service.UserService;

import java.util.*;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordHelper passwordHelper;

}
