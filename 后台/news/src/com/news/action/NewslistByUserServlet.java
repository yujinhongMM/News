package com.news.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.news.entity.News;
import com.news.service.NewsService;
import com.news.service.impl.NewsServiceImpl;

import net.sf.json.JSONObject;

/**
 * Description 通过用户名获取新闻列表
 * @author YJH
 * @date 2018.11.21
 * @category 通过用户名获取新闻列表
 */
@WebServlet("/NewslistByUserServlet")
public class NewslistByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewslistByUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		int pageSize=Integer.valueOf(request.getParameter("pageSize"));
		int pageNow=Integer.valueOf(request.getParameter("pageNow"));
		NewsService newsService=new NewsServiceImpl();
		//初始化jsonObject对象，并添加数据
		JSONObject jsonObject =new JSONObject();
	
		ArrayList<News> newsListusername=new ArrayList<>();
		try {
			newsListusername=newsService.queryNewslistByUsernameService(username,pageSize, pageNow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("newsListusername="+newsListusername);
		jsonObject.put("newsListusername", newsListusername);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
    }

}
