package com.news.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.news.service.NewsService;
import com.news.service.impl.NewsServiceImpl;

import net.sf.json.JSONObject;

/**
 * Description 通过用户名获取新闻列表的数量
 * @author YJH
 * @date 2018.11.21
 * @category 通过用户名获取新闻列表的数量
 */
@WebServlet("/NewslistTotalByUserservlet")
public class NewslistTotalByUserservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewslistTotalByUserservlet() {
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
		
		NewsService newsService=new NewsServiceImpl();
		//初始化jsonObject对象，并添加数据
		JSONObject jsonObject =new JSONObject();
		
		int total=0;
		try {
			total=newsService.newsTotalByUsernameService(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jsonObject.put("total", total);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
    }

}
