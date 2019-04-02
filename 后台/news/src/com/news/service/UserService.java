package com.news.service;

import com.news.entity.User;

/**
 * Description 用户操作逻辑控制接口
 * @author YJH
 * @date 2018.10.31
 * @category 用户操作逻辑控制接口
 */
public interface UserService {
	/**
	 * 根据用户名，查找用户头像
	 */
	public String queryUserImgByUsernameService(String username) throws Exception;
	/**
	 * 根据用户名，查找用户详细信息
	 */
	/**
	 * 注册
	 */
	/**
	 * 删除用户
	 */
	/**
	 * 修改用户信息
	 */
	/**
	 * 登录
	 */
	public User LoginService(String username,String password) throws Exception;
}
