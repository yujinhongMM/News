package com.news.action;


//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
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
 * Description 增加新闻
 * @author YJH
 * @date 2018.11.21
 * @category 增加新闻
 * 传入新闻的标题 作者 缩略图地址 内容地址 类型
 */
@WebServlet("/NewsAddModifyServlet")
public class NewsAddModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsAddModifyServlet() {
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
		Boolean flag=false;
		News news=new News();
		news.setNews_title(request.getParameter("news_title"));
		news.setUsername(request.getParameter("username"));
		news.setImg_url(request.getParameter("img_url"));
		news.setNews_content(request.getParameter("content"));
		news.setType(request.getParameter("type"));
		String isadd=request.getParameter("addmodify");
		System.out.println(isadd);
		System.out.println(news.getNews_content());
		NewsServiceImpl newsService=new NewsServiceImpl();
		if(isadd.equals("add")){
			try {
				flag=newsService.addNewsService(news);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(isadd.equals("modify")){
			news.setNews_id(Integer.valueOf(request.getParameter("news_id")));
			try {
				System.out.println(news);
				flag=newsService.modifyNewsService(news);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		jsonObject.put("flag",flag);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}
    
}
