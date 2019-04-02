package com.news.service.impl;
import com.news.dao.impl.UserDaoImpl;
import com.news.entity.User;
import com.news.service.UserService;

public class UserServiceImpl implements UserService {
	UserDaoImpl userDao=new UserDaoImpl();
	@Override
	public String queryUserImgByUsernameService(String username) throws Exception {
		
		return userDao.queryUserImgByUsername(username);
	}

	public User LoginService(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		User user=new User();
		user=userDao.isUser(username, password);
		if(user==null){
			return null;
		}
		return user;
	}

}
