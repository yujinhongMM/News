package com.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.news.dao.ReplyDao;
import com.news.entity.Reply;
import com.news.utils.DBUtils;
import com.news.utils.PageUtils;

/**
 * Description 回复评论数据库操作实现
 * @author YJH
 * @date 2018.10.31
 * @category 回复评论数据库操作实现
 */
public class ReplyDaoImpl implements ReplyDao{

	@Override
	public ArrayList<Reply> queryCommentAllReply(int comment_id, PageUtils pageUtils) throws Exception {
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<Reply> list = new ArrayList<>();
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("SELECT * FROM reply WHERE comment_id=? ORDER BY reply_time ASC LIMIT ?,?");
			//设置参数
			ps.setInt(1, comment_id);
			ps.setInt(2, start);
			ps.setInt(3, size);
			rs=ps.executeQuery();
			
				while(rs.next()){
					Reply reply=new Reply(rs.getInt("reply_id"), rs.getInt("comment_id"),
							rs.getString("reply_time"),
							rs.getString("responder"), rs.getString("reviewers"), rs.getString("content"),
							rs.getInt("isRead"), rs.getInt("likeNumber"));
					list.add(reply);
			}
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
//		System.out.println("查看"+comment_id+"评论下的全部回复:"+list);
		return list;
	}

	@Override
	public int queryCommentAllReplyNumber(int comment_id) throws Exception {
		// 查询总数的sql语句
		String sql = "SELECT COUNT(*) m FROM reply WHERE comment_id=?";
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
			ps.setInt(1, comment_id);
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
//		System.out.println("查看"+comment_id+"评论下的全部回复数数量:"+m);
		return m;
	}
	
}
