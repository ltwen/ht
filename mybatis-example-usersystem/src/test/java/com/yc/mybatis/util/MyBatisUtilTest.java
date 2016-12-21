package com.yc.mybatis.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyBatisUtilTest {

	@Test
	public void testGetAutoTranSession() {
		assertNotNull(MyBatisUtil.getAutoTranSession().getConnection());
	}

}
