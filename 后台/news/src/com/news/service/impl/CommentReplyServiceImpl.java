package com.news.service.impl;


import java.util.ArrayList;

import com.news.dao.impl.CommentDaoImpl;
import com.news.dao.impl.ReplyDaoImpl;
import com.news.dao.impl.UserDaoImpl;
import com.news.entity.Comment;
import com.news.entity.Reply;
import com.news.service.CommentReplyService;
import com.news.to.CommentReplyTo;

import com.news.utils.PageUtils;

public class CommentReplyServiceImpl implements CommentReplyService {
	ReplyDaoImpl replydao=new ReplyDaoImpl();
	CommentDaoImpl commentdao=new CommentDaoImpl();
	UserDaoImpl userdao=new UserDaoImpl();
	
	@Override
	public ArrayList<CommentReplyTo> queryAllCommentView(int news_id,int comment_pageSize,int comment_PageNow,int reply_pageSize,int reply_PageNow) throws Exception {
		
		
		ArrayList<CommentReplyTo> commentReplys=new ArrayList<CommentReplyTo>();
		
		ArrayList<Comment> comments=new ArrayList<Comment>();
		//评论分页
		int comment_totalSize=commentdao.queryNewsAllCommentNumber(news_id);
		if (comment_totalSize <= 0)
			return null;
		PageUtils comment_pageUtils = new PageUtils(comment_PageNow, comment_totalSize);
		int totalPage = comment_totalSize / comment_pageSize + 1;
		comment_pageUtils.setTotalPage(totalPage);
		comment_pageUtils.setPageSize(comment_pageSize);
		
		comments=commentdao.queryNewsAllComment(news_id, comment_pageUtils);
		
		for (int i = 0; i < comments.size(); i++ ){
			
			ArrayList<Reply> replys=new ArrayList<Reply>();
			
			
			//回复分页
			int reply_totalSize=replydao.queryCommentAllReplyNumber(comments.get(i).getComment_id());
				
			PageUtils reply_pageUtils = new PageUtils(reply_PageNow, reply_totalSize);
			int reply_totalPage = reply_totalSize / reply_pageSize + 1;
			reply_pageUtils.setTotalPage(reply_totalPage);
			reply_pageUtils.setPageSize(reply_pageSize);
			
			
			ArrayList<String> replys_head_portrait_url=new ArrayList<>();
			replys=replydao.queryCommentAllReply(comments.get(i).getComment_id(), reply_pageUtils);
			for (int j = 0; j < replys.size(); j++ ){
				replys_head_portrait_url.add(userdao.queryUserImgByUsername(replys.get(j).getResponder()));
			}
			CommentReplyTo commentReply=new CommentReplyTo(comments.get(i),replys,userdao.queryUserImgByUsername(comments.get(i).getResponder()),
					replys_head_portrait_url);
			commentReplys.add(commentReply);
		}
		//System.out.println(commentReplys);
		
		return commentReplys;
	}

	@Override
	public boolean addCommentService(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCommentService(int comment_id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int commentTotalService(int news_id) throws Exception {
		return commentdao.queryNewsAllCommentNumber(news_id);
	}

}
