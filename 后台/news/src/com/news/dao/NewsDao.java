package com.news.dao;

import java.util.ArrayList;

import com.news.entity.News;
import com.news.utils.PageUtils;

public interface NewsDao {
	/**
	 * 通过新闻类型查询新闻的条数
	 */
	public int newsTypeCount(String type)throws Exception;
	/**
	 * 按照新闻类型时间降序分页查询新闻列表
	 */
	public ArrayList<News> newsTimeList(String type,PageUtils pageUtils)throws Exception;
	/**
	 * 按照评论数对新闻进行查询前n条数据的ID
	 */
	public ArrayList<Integer> newsCommentList(int n)throws Exception;
	/**
	 * 按照新闻id查询新闻
	 */
	public News queryNewsInfo(int news_id)throws Exception;
	/**
	 * 发布新闻
	 */
	public Boolean addNews(News news)throws Exception;
	/**
	 * 按照用户名查找新闻列表
	 */
	public ArrayList<News> queryNewsListByUsername(String username,PageUtils pageUtils)throws Exception;
	/**
	 * 根据用户名查询新闻的总条数
	 */
	public int NewsTotalByusername(String username)throws Exception;
	/**
	 * 根据新闻Id修改新闻
	 */
	public Boolean modifyNews(News news)throws Exception;
	/**
	 * 根据新闻id删除新闻
	 */
	public Boolean deleteNews(int news_id)throws Exception;
}
