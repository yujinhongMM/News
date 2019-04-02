package com.news.dao;

import java.util.ArrayList;

import com.news.entity.Comment;

import com.news.utils.PageUtils;
/**
 * Description 评论数据库操作接口
 * @author YJH
 * @date 2018.10.31
 * @category 评论数据库操作接口
 */
public interface CommentDao {

	/**
	 * 增加评论
	 */
	public boolean addComment(Comment comment) throws Exception;
	/**
	 * 删除评论
	 */
	public boolean deleteComment(int comment_id) throws Exception;
	/**
	 * 修改评论的点赞数     
	 */
	public boolean likeComment(int comment_id,int likeNumber) throws Exception;
	/**
	 * 查看某个用户的所有评论
	 */
	public ArrayList<Comment> queryUserAllComment(int news_id,PageUtils pageUtils) throws Exception;
	/**
	 * 查看某条新闻的全部评论
	 */
	public ArrayList<Comment> queryNewsAllComment(int news_id,PageUtils pageUtils) throws Exception;
	/**
	 * 查看某条新闻下的全部评论数数量
	 */
	public int queryNewsAllCommentNumber(int news_id) throws Exception;
}
