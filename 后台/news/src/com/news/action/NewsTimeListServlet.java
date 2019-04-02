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
 * Servlet implementation class NewsTimeListServlet
 * Description 新闻按照type时间逆序分页查找
 * @author YJH
 * @date 2018.11.21
 * @category 新闻按照type时间逆序分页查找
 * 接收到 type(新闻类型) pageSize(分页大小) pageNow(当前页数)
 * 返回news的json对象 名字叫做newsTimeList
 */
@WebServlet("/NewsTimeListServlet")
public class NewsTimeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsTimeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		String type=request.getParameter("type");
		int pageSize=Integer.valueOf(request.getParameter("pageSize"));
		int pageNow=Integer.valueOf(request.getParameter("pageNow"));
		NewsService newsService=new NewsServiceImpl();
		//初始化jsonObject对象，并添加数据
		JSONObject jsonObject =new JSONObject();
	
		ArrayList<News> newsTimeList=new ArrayList<>();
		try {
			newsTimeList=newsService.newsTimeListService(type,pageSize, pageNow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("newsTimeList"+newsTimeList);
		jsonObject.put("newsTimeList", newsTimeList);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
    }
    

}
