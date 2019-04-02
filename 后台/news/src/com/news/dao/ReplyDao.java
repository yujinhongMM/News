package com.news.dao;

import java.util.ArrayList;

import com.news.entity.Reply;
import com.news.utils.PageUtils;

/**
 * Description 回复评论数据库操作接口
 * @author YJH
 * @date 2018.10.31
 * @category 回复评论数据库操作接口
 */
public interface ReplyDao {
	/**
	 * 查看某个评论下的，所有回复
	 */
	public ArrayList<Reply> queryCommentAllReply(int comment_id,PageUtils pageUtils) throws Exception;
	/**
	 * 查看某个评论下的，所有回复的数量
	 */
	public int queryCommentAllReplyNumber(int comment_id) throws Exception;
}
