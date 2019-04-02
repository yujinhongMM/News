package com.news.entity;
/**
 * Description 新闻
 * @author YJH
 * @date 2018.10.31
 * @category 新闻
 */
public class News {
	private int news_id;//新闻Id
	private String username;//用户名
	private String news_title;//新闻标题
	private String news_content;//新闻内容
	private String news_time;//新闻发布时间
	private String img_url;//图片地址
	private String type;//类型
	@Override
	public String toString() {
		return "News [news_id=" + news_id + ", username=" + username + ", news_title=" + news_title + ", news_content="
				+ news_content + ", news_time=" + news_time + ", img_url=" + img_url + ", type=" + type + "]";
	}
	public News(int news_id, String username, String news_title, String news_content, String news_time, String img_url,
			String type) {
		super();
		this.news_id = news_id;
		this.username = username;
		this.news_title = news_title;
		this.news_content = news_content;
		this.news_time = news_time;
		this.img_url = img_url;
		this.type = type;
	}
	public News() {
		super();
	}
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public String getNews_time() {
		return news_time;
	}
	public void setNews_time(String news_time) {
		this.news_time = news_time;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public News(int news_id, String news_title, String img_url) {
		super();
		this.news_id = news_id;
		this.news_title = news_title;
		this.img_url = img_url;
	}
	
	
}
