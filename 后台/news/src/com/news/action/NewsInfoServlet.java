package com.news.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.news.entity.News;
import com.news.service.impl.NewsServiceImpl;

import net.sf.json.JSONObject;
/**
 * Description 新闻详细信息
 * @author YJH
 * @date 2018.11.21
 * @category 新闻详细信息
 */
@WebServlet("/NewsInfoServlet")
public class NewsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		//初始化jsonObject对象，并添加数据
		JSONObject jsonObject =new JSONObject(); 
		
		int news_id=Integer.valueOf(request.getParameter("news_id"));
		News news=new News();
		
		NewsServiceImpl newsService=new NewsServiceImpl();
		
		try {
			news=newsService.NewsInfoByidService(news_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jsonObject.put("newsInfo",news);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
