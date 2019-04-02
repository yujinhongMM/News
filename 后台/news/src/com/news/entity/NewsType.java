package com.news.entity;
/**
 * Description 新闻类型
 * @author YJH
 * @date 2018.10.31
 * @category 新闻类型
 */
public class NewsType {
	private String type;

	@Override
	public String toString() {
		return "NewsType [type=" + type + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public NewsType() {
		super();
	}
	
}
