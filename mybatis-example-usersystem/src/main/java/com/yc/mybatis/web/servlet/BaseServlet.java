package com.yc.mybatis.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// 直接响应字符串处理
	/**
	 * @param response
	 *            响应处理对象
	 * @param outStr
	 *            响应的数据
	 * @throws IOException
	 */
	protected void respOutStr(HttpServletResponse response, String outStr) throws IOException {
		PrintWriter out = response.getWriter();
		out.println(outStr);
		out.flush();
		out.close();
	}

	protected String toJsonStr(Object obj) {
		//Gson gson = new Gson();
		//设置Gson转换时， 日期的转换格式
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(obj);
	}
	
	protected SmartUpload getSmartUpload(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			SmartUpload su=new SmartUpload();
			su.setCharset("utf-8");
			su.initialize(getServletConfig(), req, resp);
			su.upload();
			return su;
		} catch (ServletException | SmartUploadException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Class为类， 接口的类型， 类似 int 为 整形的类型
	// UserServlet.class 的类型是Class, 类似 1 的类型是int
	// Class为类的抽象
	protected <T> T getReqParam2Obj(HttpServletRequest request, Class<T> clazz) {
		// 1.取到要转换成的对象的实例
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			LogManager.getLogger().error("创建" + clazz.getName() + "实例对象失败!!!");
		}

		// 2.取到所有的请求参数名
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String valName = paramNames.nextElement();
			String val = request.getParameter(valName);
			// 3.根据请求参数名，通过反射取到对应的实体类中的方法对象
			String suff = String.format("et%c%s", Character.toUpperCase(valName.charAt(0)), valName.substring(1));

			try {
				Class<?> paramType = clazz.getMethod("g" + suff).getReturnType(); // 取到getXxx()方法的返回类型的类的类对象
				Method sm = clazz.getMethod("s" + suff, paramType); // 取到setXxx()方法对象

				if (paramType.getName().intern() == int.class.getName().intern() || 
						paramType.getName().intern() == Integer.class.getName().intern()) {
					sm.invoke(t, Integer.parseInt(val));
				} else if (paramType.getName().intern() == double.class.getName().intern() || 
						paramType.getName().intern() == Double.class.getName().intern()) {
					sm.invoke(t, Double.parseDouble(val));
				} else {
					sm.invoke(t, val);
				}
			} catch (NoSuchMethodException | SecurityException e) {
				LogManager.getLogger().warn("在" + clazz.getName() + "中没有对应" + e.getMessage() + "方法");
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				LogManager.getLogger().warn(e.getMessage() + "转换入有参数格式不正确，转换数据类型失败");
			}
		}
		return t;
	}
	
	protected <T> T getReqParam2Obj(Request request, Class<T> clazz) {
		// 1.取到要转换成的对象的实例
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			LogManager.getLogger().error("创建" + clazz.getName() + "实例对象失败!!!");
		}

		// 2.取到所有的请求参数名
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String valName = paramNames.nextElement();
			String val = request.getParameter(valName);
			// 3.根据请求参数名，通过反射取到对应的实体类中的方法对象
			String suff = String.format("et%c%s", Character.toUpperCase(valName.charAt(0)), valName.substring(1));

			try {
				Class<?> paramType = clazz.getMethod("g" + suff).getReturnType(); // 取到getXxx()方法的返回类型的类的类对象
				Method sm = clazz.getMethod("s" + suff, paramType); // 取到setXxx()方法对象

				if (paramType.getName().intern() == int.class.getName().intern() || 
						paramType.getName().intern() == Integer.class.getName().intern()) {
					sm.invoke(t, Integer.parseInt(val));
				} else if (paramType.getName().intern() == double.class.getName().intern() || 
						paramType.getName().intern() == Double.class.getName().intern()) {
					sm.invoke(t, Double.parseDouble(val));
				} else {
					sm.invoke(t, val);
				}
			} catch (NoSuchMethodException | SecurityException e) {
				LogManager.getLogger().warn("在" + clazz.getName() + "中没有对应" + e.getMessage() + "方法");
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				LogManager.getLogger().warn(e.getMessage() + "转换入有参数格式不正确，转换数据类型失败");
			}
		}
		return t;
	}
}
