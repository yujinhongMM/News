package com.news.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.news.service.impl.CommentReplyServiceImpl;
import com.news.to.CommentReplyTo;

import net.sf.json.JSONObject;

/**
 * Description 查看评论回复
 * @author YJH
 * @date 2018.11.21
 * @category 查看评论回复
 * 
 */
@WebServlet("/CommentReplyServlet")
public class CommentReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CommentReplyServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out=response.getWriter();
		//初始化jsonObject对象，并添加数据
		JSONObject jsonObject =new JSONObject(); 
		
		CommentReplyServiceImpl commentServiceImpl=new CommentReplyServiceImpl();
		List<CommentReplyTo> commentReply=new ArrayList<CommentReplyTo>();
		int NewsID=Integer.valueOf(request.getParameter("NewsID"));
		int commentsize=Integer.valueOf(request.getParameter("commentsize"));
		int commentcurrentPage=Integer.valueOf(request.getParameter("commentcurrentPage"));
		int replypagesize=Integer.valueOf(request.getParameter("replypagesize"));
		int replypagecurrentPage=Integer.valueOf(request.getParameter("replypagecurrentPage"));
		try {
			commentReply = commentServiceImpl.queryAllCommentView(NewsID, commentsize, commentcurrentPage, replypagesize, replypagecurrentPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jsonObject.put("commentReply",commentReply);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}


}
