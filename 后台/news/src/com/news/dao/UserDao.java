package com.news.dao;

import com.news.entity.User;

/**
 * Description 用户数据库操作接口
 * @author YJH
 * @date 2018.10.31
 * @category 用户数据库操作接口
 */
public interface UserDao {
	/**
	 * 根据用户名查找用户头像
	 */
	public String queryUserImgByUsername(String username) throws Exception;
	/**
	 * 根据用户名，密码判断用户是否存在并返回用户个人信息
	 */
	public User isUser(String username,String password) throws Exception;
	/**
	 * 根据用户名判断用户名是否存在
	 */
	public Boolean isUsername(String username) throws Exception;
	/**
	 * 增加新用户
	 */
	public Boolean addUser(User user) throws Exception;
	
}
