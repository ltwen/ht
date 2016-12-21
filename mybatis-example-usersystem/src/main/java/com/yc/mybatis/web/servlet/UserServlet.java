 package com.yc.mybatis.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.yc.mabatis.serviceImp.UserServiceImp;
import com.yc.mybatis.entity.PagenationBean;
import com.yc.mybatis.entity.User;
import com.yc.mybatis.service.UserService;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
	
	private static final long serialVersionUID = 513537102242703475L;
	private UserService userService;
	
	public UserServlet() {
		userService=new UserServiceImp();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String strUri=req.getRequestURI();
		if(strUri.endsWith("login")){
			doLogin(req,resp);
		}else if(strUri.endsWith("list")){
			dolist(req,resp);
		}else if(strUri.endsWith("modify")){
			doModify(req,resp);
		}
	}

	private void doModify(HttpServletRequest request, HttpServletResponse resp) throws IOException {
		SmartUpload su=getSmartUpload(request,resp);
		Request req=su.getRequest(); //smartupload的request取参数
		
		//取上传文件数据
		File f=su.getFiles().getFile(0);
		System.out.println(f.getFieldName());
		if(!f.isMissing()){
			String path=new java.io.File(getServletContext().getRealPath("/")).getParent()+"\\upload\\"+f.getFileName();
			System.out.println("上传文件的保存位置："+path);
			//RealPath ===>D:\Program Files (x86)\MysoftWord\
			//apache-tomcat-7.0.70\webapps\mybatis-example-usersystem\
			try {
				f.saveAs(path,SmartUpload.SAVE_PHYSICAL);
//				System.out.println(System.getProperty("user.dir"));
//				f.saveAs("D:\\workspaces\\mybatis-example-usersystem\\src\\main\\webapp\\image\\"+f.getFileName(),SmartUpload.SAVE_PHYSICAL);
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		User user=getReqParam2Obj(req, User.class);
		user.setPicPath("/upload/"+f.getFileName());
		System.out.println("UserServlet.modify==> user:"+user);
		respOutStr(resp, toJsonStr(userService.modifyUser(user)));
	}
	
	
	private void dolist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String currage=req.getParameter("page");
		String pageSize=req.getParameter("rows");
		
		PagenationBean<User> userbean=userService.listPartUser(currage,pageSize);
		
		respOutStr(resp, toJsonStr(userbean));
	}
	private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User user=getReqParam2Obj(req, User.class);
		respOutStr(resp, userService.login(user)+"");
	}
	
}
