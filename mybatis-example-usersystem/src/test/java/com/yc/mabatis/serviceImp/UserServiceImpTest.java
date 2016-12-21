package com.yc.mabatis.serviceImp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.yc.mybatis.entity.PagenationBean;
import com.yc.mybatis.entity.User;
import com.yc.mybatis.service.UserService;

public class UserServiceImpTest extends UserServiceImp {

	private UserService userService;
	
	@Before
	public void setUp(){
		userService=new UserServiceImp();
	}
	@Test
	public void testLogin() {
		User user= new User();
		user.setId(10000);
		user.setName("xrwverqmzvscspfhrv");
		boolean result=userService.login(user);
		assertEquals(true,result);
	}

	@Test
	public void testGetPartUser() {
		PagenationBean<User> userBean=userService.listPartUser(null, null);
		System.out.println(userBean);
		assertNotNull(userBean);
	}
}
