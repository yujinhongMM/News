package com.news.entity;


/**
 * Description 评论
 * @author YJH
 * @date 2018.10.31
 * @category 评论
 */
public class Comment {
	private int comment_id;//评论id
	private int news_id;//新闻id
	private String comment_time;//评论时间
	private String responder;//评论者
	private String reviewers;//被评论者
	private String content;//评论内容
	private int isRead;//消息是否已读
	private int likeNumber;//点赞数
	
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
	public String getComment_time() {
		return comment_time;
	}
	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}
	public String getResponder() {
		return responder;
	}
	public void setResponder(String responder) {
		this.responder = responder;
	}
	public String getReviewers() {
		return reviewers;
	}
	public void setReviewers(String reviewers) {
		this.reviewers = reviewers;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public int getLikeNumber() {
		return likeNumber;
	}
	public void setLikeNumber(int likeNumber) {
		this.likeNumber = likeNumber;
	}
	
	@Override
	public String toString() {
		return "Comment [comment_id=" + comment_id + ", news_id=" + news_id + ", comment_time=" + comment_time
				+ ", responder=" + responder + ", reviewers=" + reviewers + ", content=" + content + ", isRead="
				+ isRead + ", likeNumber=" + likeNumber + "]";
	}
	
	public Comment() {
		super();
	}
	public Comment(int comment_id, int news_id, String comment_time, String responder, String reviewers, String content,
			int isRead, int likeNumber) {
		super();
		this.comment_id = comment_id;
		this.news_id = news_id;
		this.comment_time = comment_time;
		this.responder = responder;
		this.reviewers = reviewers;
		this.content = content;
		this.isRead = isRead;
		this.likeNumber = likeNumber;
	}
	
}
