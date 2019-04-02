package com.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.news.dao.UserDao;
import com.news.entity.User;
import com.news.utils.DBUtils;
import com.news.utils.MdPwdUtil;


public class UserDaoImpl implements UserDao{

	@Override
	public String queryUserImgByUsername(String username) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String head_portrait_url=null;
		String sql="SELECT head_portrait_url FROM user WHERE username=?";
		try {
			conn=DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			//设置参数
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				head_portrait_url=rs.getString("head_portrait_url");
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
//		System.out.println("查看"+username+"头像:"+head_portrait_url);
		return head_portrait_url;
	}

	@Override
	public User isUser(String username, String password) throws Exception {
		// 获取链接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=new User();
		try {
			conn=DBUtils.getConnection();
			ps=conn.prepareStatement("select * from user where username=? and password=?");
			//设置参数
			ps.setString(1, username);
			ps.setString(2, MdPwdUtil.MD5Password(password));
			
			rs=ps.executeQuery();
			if(rs.next()){
				
				//user.setUser_id(rs.getInt("user_id"));
				user.setUsername(username);
				user.setPassword(MdPwdUtil.MD5Password(password));
				user.setBirthday(rs.getString("birthday"));
				user.setEmail(rs.getString("email"));
				user.setHead_portrait_url(rs.getString("head_portrait_url"));
				user.setPower(rs.getInt("power"));
				user.setSex(rs.getString("sex"));
				
			}else{
				user=null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, ps, conn);
		}
		System.out.println("用户密码正确，用户信息为："+user);
		return user;
	}

	@Override
	public Boolean isUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
