package com.news.action;
/**
 * Servlet implementation class CommentReplyServlet
 * 
 * 获取评论总条数
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.news.service.impl.CommentReplyServiceImpl;

import net.sf.json.JSONObject;
/**
 * Servlet implementation class NewsTimeListServlet
 * Description 评论的总条数
 * @author YJH
 * @date 2018.11.21
 * @category 评论的总条数
 * 接收到 NewsID
 * 返回total的json对象
 */
@WebServlet("/CommentTotalServlet")
public class CommentTotalServlet  extends HttpServlet {
	
	private static final long serialVersionUID = 1L; 
    
    public CommentTotalServlet() {
        super();
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		int NewsID=Integer.valueOf(request.getParameter("NewsID"));
		//初始化jsonObject对象，并添加数据
		JSONObject jsonObject =new JSONObject(); 
		CommentReplyServiceImpl commentReplyServlet=new  CommentReplyServiceImpl();
		int total=0;
		try {
			total=commentReplyServlet.commentTotalService(NewsID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonObject.put("total",total);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
    }
}
