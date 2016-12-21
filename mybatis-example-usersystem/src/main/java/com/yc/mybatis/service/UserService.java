package com.yc.mybatis.service;

import com.yc.mybatis.entity.PagenationBean;
import com.yc.mybatis.entity.User;

public interface UserService {

	boolean login(User user);

	PagenationBean<User> listPartUser(String currage, String pageSize);

	boolean modifyUser(User user);

}
