package com.yc.mybatis.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;

public class MyBatisUtil {
	private static SqlSessionFactory factory;
	static{
		InputStream in=null;
		try {
			in= Resources.getResourceAsStream("mybatis.xml");
			LogManager.getLogger().debug("加载类路径下的mybatis框架配置成功");
		} catch (IOException e) {
			LogManager.getLogger().error("加载类路径下的mybatis框架配置失败",e);
		}
		
		factory =new SqlSessionFactoryBuilder().build(in);
		LogManager.getLogger().debug("根据加载上来的myatis配置文件信息，构建对象成功...");
		
	}
	
	//取到数据库会话对象
	public  static SqlSession getAutoTranSession(){
		SqlSession session=null;
		if(factory!=null){
			session=factory.openSession(true);//自动提交
			LogManager.getLogger().debug("根据加载上来的myatis配置文件信息，构建对象成功...");
			return session; 
		}
		return null;
	}
}
