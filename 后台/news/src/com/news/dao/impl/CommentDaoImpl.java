package com.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.news.dao.CommentDao;
import com.news.entity.Comment;
import com.news.utils.DBUtils;
import com.news.utils.PageUtils;

/**
 * Description 评论数据库操作实现
 * @author YJH
 * @date 2018.10.31
 * @category 评论数据库操作实现
 */


public class CommentDaoImpl implements CommentDao {

	@Override
	public boolean addComment(Comment comment) throws Exception {
		return false;
	}

	@Override
	public boolean deleteComment(int comment_id) throws Exception {
		
		return false;
	}

	@Override
	public boolean likeComment(int comment_id, int likeNumber) throws Exception {
		
		return false;
	}

	@Override
	public ArrayList<Comment> queryUserAllComment(int news_id, PageUtils pageUtils) throws Exception {
		
		return null;
	}

	@Override
	public ArrayList<Comment> queryNewsAllComment(int news_id, PageUtils pageUtils) throws Exception {
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<Comment> list = new ArrayList<>();
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("SELECT * FROM comment WHERE news_id=? ORDER BY comment_time DESC LIMIT ?,?");
			//设置参数
			ps.setInt(1, news_id);
			ps.setInt(2, start);
			ps.setInt(3, size);
			rs=ps.executeQuery();
			
				while(rs.next()){
					Comment comment=new Comment(rs.getInt("comment_id"), rs.getInt("news_id"),
							rs.getString("comment_time"),
							rs.getString("responder"), rs.getString("reviewers"), rs.getString("content"),
							rs.getInt("isRead"), rs.getInt("likeNumber"));
					list.add(comment);
			}
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
//		System.out.println("查看"+news_id+"新闻下的全部评论:"+list);
		return list;
	}

	@Override
	public int queryNewsAllCommentNumber(int news_id) throws Exception {
		// 查询总数的sql语句
		String sql = "SELECT COUNT(*) m FROM comment WHERE news_id=?";
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int m = 0;
		try {
			conn=DBUtils.getConnection();
			
			// 查询方案总数
			ps = conn.prepareStatement(sql);
			//设置参数
			ps.setInt(1, news_id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				m = rs.getInt("m");
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
//		System.out.println("查看"+news_id+"新闻下的全部评论数数量:"+m);
		return m;
	}
}
	
