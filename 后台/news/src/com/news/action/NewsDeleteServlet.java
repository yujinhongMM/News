package com.news.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.news.service.impl.NewsServiceImpl;

import net.sf.json.JSONObject;

/**
 * Description 删除新闻
 * @author YJH
 * @date 2018.11.21
 * @category 删除新闻
 */
@WebServlet("/NewsDeleteServlet")
public class NewsDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsDeleteServlet() {
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
		boolean flag=false;
		
		NewsServiceImpl newsService=new NewsServiceImpl();
		
		try {
			flag=newsService.deleteNewsService(news_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jsonObject.put("flag",flag);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

}
