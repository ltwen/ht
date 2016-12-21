package com.yc.mybatis.mapper;

import com.yc.mybatis.entity.PagenationBean;
import com.yc.mybatis.entity.User;

public interface UserMapper {

	User findUser(User user);

	PagenationBean<User> getUserByPagenation(PagenationBean<User> userBean);

	int updateUser(User user);
	
	User getUserId(int id);

}
