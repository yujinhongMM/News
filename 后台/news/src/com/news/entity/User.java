package com.news.entity;
/**
 * Description 用户
 * @author YJH
 * @date 2018.10.31
 * @category 用户
 */
public class User {
	private int user_id;//用户id
	private String username;//用户名
	private String password;//密码
	private String sex;//性别
	private int power;//权限
	private String birthday;//生日
	private String email;//邮箱
	private String head_portrait_url;//头像地址
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", sex=" + sex
				+ ", power=" + power + ", birthday=" + birthday + ", email=" + email + ", head_portrait_url="
				+ head_portrait_url + "]";
	}
	public User(int user_id, String username, String password, String sex, int power, String birthday, String email,
			String head_portrait_url) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.power = power;
		this.birthday = birthday;
		this.email = email;
		this.head_portrait_url = head_portrait_url;
	}
	public User() {
		super();
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHead_portrait_url() {
		return head_portrait_url;
	}
	public void setHead_portrait_url(String head_portrait_url) {
		this.head_portrait_url = head_portrait_url;
	}
	
}
