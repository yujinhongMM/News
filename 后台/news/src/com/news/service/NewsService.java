package com.news.service;

import java.util.ArrayList;

import com.news.entity.News;

public interface NewsService {
	/**
	 * 按照新闻类型时间降序分页查询新闻列表
	 */
	public ArrayList<News> newsTimeListService(String type,int pageSize,int pageNow)throws Exception;
	/**
	 * 按照新闻类型查找新闻的总条数
	 */
	public int newsTypeCountService(String type)throws Exception;
	/**
	 * 读出新闻的热点排行榜，传入一个排行榜显示前几名 n 
	 */
	public ArrayList<News> newsRankingServer(int n)throws Exception;
	/**
	 * 发布新闻
	 */
	public Boolean addNewsService(News news)throws Exception;
	/**
	 * 根据新闻id查询新闻详细信息
	 */
	public News NewsInfoByidService(int news_id)throws Exception;
	/**
	 * 查询用户发表的新闻列表
	 */
	public ArrayList<News> queryNewslistByUsernameService(String username,int pageSize,int pageNow)throws Exception;
	/**
	 * 查询用户发表的新闻的条数
	 */
	public int newsTotalByUsernameService(String username)throws Exception;
	/**
	 * 根据新闻Id修改新闻
	 */
	public Boolean modifyNewsService(News news)throws Exception;
	/**
	 * 根据新闻id删除新闻
	 */
	public Boolean deleteNewsService(int news_id)throws Exception;
}
