package com.yc.mybatis.mapper;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import com.yc.mybatis.entity.User;
import com.yc.mybatis.util.MyBatisUtil;

public class UserMapperTest {
	private UserMapper userMapper;
	private UserMapper userMapper02;
	SqlSession session;
	
	@Before
	public void setUp(){
		session=MyBatisUtil.getAutoTranSession();
				userMapper=session.getMapper(UserMapper.class);
				userMapper02=session.getMapper(UserMapper.class);
	}
	@Test //一级缓存也叫session缓存 同一查询语句不会清除，增删改查询语句会清除缓存
	public void testGetUserId() {
		long start=System.currentTimeMillis();
		User user= userMapper.getUserId(10089);
		System.out.println("第一次取:"+user);
		//session.clearCache(); //清除缓存
		System.out.println("===============");
		user= userMapper.getUserId(10099);
		System.out.println("第一次取:"+user);
		//session.clearCache(); //清除缓存
		System.out.println("===============");
		user= userMapper.getUserId(10089);
		System.out.println("第二次取:"+user);
		long end=System.currentTimeMillis();
		long time=end-start;
		System.out.println(time);
	}
	@Test
	public void testGetUserId02() {
		long start=System.currentTimeMillis();
		User user= userMapper.getUserId(10089);
		System.out.println("第一次取:"+user);
		//session.clearCache(); //清除缓存
		System.out.println("===============");
		user= userMapper.getUserId(10099);
		user.setName("小明");
		userMapper.updateUser(user);
		System.out.println("第一次取:"+user);
		//session.clearCache(); //清除缓存
		System.out.println("===============");
		user= userMapper.getUserId(10089);
		System.out.println("第二次取:"+user);
		long end=System.currentTimeMillis();
		long time=end-start;
		System.out.println(time);
	}

}
