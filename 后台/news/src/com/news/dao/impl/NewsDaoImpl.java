package com.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import com.news.dao.NewsDao;
import com.news.entity.News;
import com.news.utils.DBUtils;
import com.news.utils.PageUtils;

public class NewsDaoImpl implements NewsDao {

	@Override
	public int newsTypeCount(String type) throws Exception {
		// 查询总数的sql语句
		String sql = "SELECT COUNT(*) m FROM news where type=?";
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int m = 0;
		try {
			conn=DBUtils.getConnection();
			// 查询方案总数
			ps = conn.prepareStatement(sql);
			ps.setString(1, type);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				m = rs.getInt("m");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
//			System.out.println("查看"+type+"新闻下的全部数量:"+m);
		return m;
	}

	@Override
	public ArrayList<News> newsTimeList(String type,PageUtils pageUtils) throws Exception {
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<News> list = new ArrayList<>();
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("SELECT * FROM news WHERE type=? ORDER BY news_time DESC LIMIT ?,?");
			//设置参数
			ps.setString(1, type);
			ps.setInt(2, start);
			ps.setInt(3, size);
			rs=ps.executeQuery();
			
				while(rs.next()){
					News news=new News(rs.getInt("news_id"),
							rs.getString("news_title"),
							rs.getString("img_url"));
					list.add(news);
				}
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
//	System.out.println("查看"+type+"新闻:"+list);
		return list;
	}

	@Override
	public ArrayList<Integer> newsCommentList(int n) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<Integer> list = new ArrayList<>();
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("select news_id,count(*) m from comment group by news_id order by m desc LIMIT 0,?");
			//设置参数
			ps.setInt(1, n);
			rs=ps.executeQuery();
			
				while(rs.next()){
					list.add(rs.getInt("news_id"));
				}
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
	//	System.out.println("list"+list);
		return list;
	}

	@Override
	public News queryNewsInfo(int news_id) throws Exception {
		News news=new News();
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("select * from news where news_id=?");
			//设置参数
			ps.setInt(1, news_id);
			rs=ps.executeQuery();
			if (rs.next()) {
				news.setNews_id(rs.getInt("news_id"));
				news.setNews_content(rs.getString("news_content"));
				news.setNews_time(rs.getString("news_time"));
				news.setNews_title(rs.getString("news_title"));
				news.setImg_url(rs.getString("img_url"));
				news.setType(rs.getString("type"));
				news.setUsername(rs.getString("username"));
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
	//	System.out.println("news"+news);
		return news;
	}

	@Override
	public Boolean addNews(News news) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("insert into news(username,news_title,news_content,news_time,img_url,type) values (?,?,?,?,?,?)");
			ps.setString(1,news.getUsername());
			ps.setString(2,news.getNews_title());
			ps.setString(3,news.getNews_content());
			ps.setString(4,news.getNews_time());
			ps.setString(5,news.getImg_url());
			ps.setString(6,news.getType());
			int i=ps.executeUpdate();
			if(i>0){
				System.out.println("新闻插入success");
				flag=true;
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		return flag;
	}

	@Override
	public ArrayList<News> queryNewsListByUsername(String username,PageUtils pageUtils) throws Exception {
		int start = (pageUtils.getPageNow() - 1) * pageUtils.getPageSize();
		int size = pageUtils.getPageSize();
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<News> list = new ArrayList<>();
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("SELECT * FROM news WHERE username=? ORDER BY news_time DESC LIMIT ?,?");
			//设置参数
			ps.setString(1, username);
			ps.setInt(2, start);
			ps.setInt(3, size);
			rs=ps.executeQuery();
			
				while(rs.next()){
					News news=new News();
					news.setNews_id(rs.getInt("news_id"));
					news.setNews_time(rs.getString("news_time"));
					news.setNews_title(rs.getString("news_title"));
					news.setImg_url(rs.getString("img_url"));
					list.add(news);
				}
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}

		return list;
	}

	@Override
	public int NewsTotalByusername(String username) throws Exception {
		// 查询总数的sql语句
		String sql = "SELECT COUNT(*) m FROM news where username=?";
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int m = 0;
		try {
			conn=DBUtils.getConnection();
			// 查询方案总数
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				m = rs.getInt("m");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println("查看"+username+"全部新闻数量:"+m);
		return m;
	}

	@Override
	public Boolean modifyNews(News news) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Boolean flag=false;
		try {
			String sql = "UPDATE news SET news_title=?,news_content=?,img_url=?,type=? WHERE news_id=?";
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, news.getNews_title());
			ps.setString(2, news.getNews_content());
			ps.setString(3, news.getImg_url());
			ps.setString(4, news.getType());
			ps.setInt(5, news.getNews_id());
			int i=ps.executeUpdate();
			System.out.println(i);
				flag=true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println(flag);
		return flag;
	}

	@Override
	public Boolean deleteNews(int news_id) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Boolean flag=false;
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("DELETE FROM news where news_id=?");
			ps.setInt(1, news_id);
			int i=ps.executeUpdate();
			System.out.println("DELETE: " + i);
			flag=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		//System.out.println(flag);
		return flag;
	}

}
