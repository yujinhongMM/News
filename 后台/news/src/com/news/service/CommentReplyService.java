package com.news.service;

import java.util.ArrayList;

import com.news.entity.Comment;
import com.news.to.CommentReplyTo;
/**
 * Description 评论回复操作逻辑控制接口
 * @author YJH
 * @date 2018.10.31
 * @category 评论回复操作逻辑控制接口
 */

public interface CommentReplyService {
	/**
	 * 根据新闻id，评论/回复每一页的大小和所在页数，查询全部评论回复列表
	 */
	public ArrayList<CommentReplyTo> queryAllCommentView(int news_id,int comment_pageSize,int comment_PageNow,int reply_pageSize,int reply_PageNow) throws Exception;
	/**
	 * 用户增加评论
	 */
	public boolean addCommentService(Comment comment) throws Exception;
	/**
	 * 用户删除评论
	 */
	public boolean deleteCommentService(int comment_id) throws Exception;
	/**
	 * 获取评论条数
	 */
	public int commentTotalService(int news_id) throws Exception;
	/**
	 * 用户给评论点赞
	 */
	/**
	 * 用户查看自己的回复
	 */
}
