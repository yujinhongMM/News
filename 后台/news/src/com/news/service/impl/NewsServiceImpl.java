package com.news.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.news.dao.impl.NewsDaoImpl;
import com.news.entity.News;
import com.news.service.NewsService;
import com.news.utils.PageUtils;

public class NewsServiceImpl implements NewsService {

	NewsDaoImpl newsDao=new NewsDaoImpl();
	

	@Override
	public ArrayList<News> newsTimeListService(String type, int pageSize, int pageNow) throws Exception {
		int totalSize=newsDao.newsTypeCount(type);
		if (totalSize <= 0)
			return null;
		PageUtils pageUtils = new PageUtils(pageNow, totalSize);
		int totalPage = totalSize / pageSize + 1;
		pageUtils.setTotalPage(totalPage);
		pageUtils.setPageSize(pageSize);
		
		return newsDao.newsTimeList(type, pageUtils);
	}

	@Override
	public int newsTypeCountService(String type) throws Exception {
		return newsDao.newsTypeCount(type);
	}

	@Override
	public ArrayList<News> newsRankingServer(int n) throws Exception {
		ArrayList<News> newslist=new ArrayList<>();
		ArrayList<Integer> newsId=new ArrayList<>();
		newsId=newsDao.newsCommentList(n);
		int i;
		for(i=0;i<newsId.size();i++){
			News news=new News();
			news=newsDao.queryNewsInfo(newsId.get(i));
			newslist.add(news);
		}
		return newslist;
	}

	@Override
	public Boolean addNewsService(News news) throws Exception {
		Boolean flag=false;
		//我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        news.setNews_time(sdf.format(date));
		if(newsDao.addNews(news)){
			flag=true;
		}
		return flag;
	}

	@Override
	public News NewsInfoByidService(int news_id) throws Exception {
		return newsDao.queryNewsInfo(news_id);
	}

	@Override
	public ArrayList<News> queryNewslistByUsernameService(String username, int pageSize, int pageNow) throws Exception {
		int totalSize=newsDao.NewsTotalByusername(username);
		if (totalSize <= 0)
			return null;
		PageUtils pageUtils = new PageUtils(pageNow, totalSize);
		int totalPage = totalSize / pageSize + 1;
		pageUtils.setTotalPage(totalPage);
		pageUtils.setPageSize(pageSize);
		return newsDao.queryNewsListByUsername(username, pageUtils);
	}

	@Override
	public int newsTotalByUsernameService(String username) throws Exception {
		return newsDao.NewsTotalByusername(username);
	}

	@Override
	public Boolean modifyNewsService(News news) throws Exception {
		return newsDao.modifyNews(news);
	}

	@Override
	public Boolean deleteNewsService(int news_id) throws Exception {
		// TODO Auto-generated method stub
		return newsDao.deleteNews(news_id);
	}
	

}
