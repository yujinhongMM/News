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
 * Description 新闻排行榜
 * @author YJH
 * @date 2018.11.21
 * @category 新闻排行榜
 */
@WebServlet("/NewsRankingListServlet")
public class NewsRankingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsRankingListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
	
		int n=Integer.valueOf(request.getParameter("count"));
		
		NewsService newsService=new NewsServiceImpl();
		//初始化jsonObject对象，并添加数据
		JSONObject jsonObject =new JSONObject();
		
		
		ArrayList<News> newsRankingList=new ArrayList<>();
		try {
			newsRankingList=newsService.newsRankingServer(n);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("newsRankingList"+newsRankingList);
		jsonObject.put("newsRankingList", newsRankingList);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
    }

}
