package com.news.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.entity.User;
import com.news.service.impl.UserServiceImpl;

import net.sf.json.JSONObject;
/**
 * Description 登录注册
 * @author YJH
 * @date 2018.11.21
 * @category 登录注册
 */
@WebServlet("/LoginRegisterServlet")
public class LoginRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession();
		int type=Integer.valueOf(request.getParameter("type"));
		UserServiceImpl userServiceImpl=new UserServiceImpl();
		//初始化jsonObject对象，并添加数据
		JSONObject jsonObject =new JSONObject();
		User user=new User();
		if(type==1){//登录
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			try {
				user=userServiceImpl.LoginService(username, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(user!=null){
				jsonObject.put("user",user);
				session.setAttribute("user", user);
				out.write(jsonObject.toString());
			}else{
				out.write("error");
			}
		}else{//注册
			
		}
		out.flush();
		out.close();
    }
    
	

}
