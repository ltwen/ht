package com.yc.mabatis.serviceImp;

import com.yc.mybatis.entity.PagenationBean;
import com.yc.mybatis.entity.User;
import com.yc.mybatis.mapper.UserMapper;
import com.yc.mybatis.service.UserService;
import com.yc.mybatis.util.MyBatisUtil;

public class UserServiceImp implements UserService{
	private UserMapper userMapper;
	
	public UserServiceImp() {
		userMapper=MyBatisUtil.getAutoTranSession().getMapper(UserMapper.class);
	}
	
	@Override
	public boolean login(User user) {
		return userMapper.findUser(user)!=null;
	}

	@Override
	public PagenationBean<User> listPartUser(String currage, String pageSize) {
		PagenationBean<User> userBean=new PagenationBean<User>();
		if(currage!=null){
			userBean.setCurrPage(Integer.parseInt(currage));
		}if(pageSize!=null){
			userBean.setPageSize(Integer.parseInt(pageSize));
		}
		return userMapper.getUserByPagenation(userBean);
	}

	@Override
	public boolean modifyUser(User user) {
		
		return userMapper.updateUser(user)>0;
	}

}
