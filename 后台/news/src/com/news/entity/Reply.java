package com.news.entity;


/**
 * Description 评论回复
 * @author YJH
 * @date 2018.10.31
 * @category 评论回复
 */
public class Reply {
	private int reply_id;//回复id
	private int comment_id;//评论id
	private String reply_time;//回复时间
	private String responder;//评论者
	private String reviewers;//被评论者
	private String content;//评论内容
	private int isRead;//消息是否已读
	private int likeNumber;//点赞数
	@Override
	public String toString() {
		return "Reply [reply_id=" + reply_id + ", comment_id=" + comment_id + ", reply_time=" + reply_time
				+ ", responder=" + responder + ", reviewers=" + reviewers + ", content=" + content + ", isRead="
				+ isRead + ", likeNumber=" + likeNumber + "]";
	}
	public Reply(int reply_id, int comment_id, String reply_time, String responder, String reviewers, String content,
			int isRead, int likeNumber) {
		super();
		this.reply_id = reply_id;
		this.comment_id = comment_id;
		this.reply_time = reply_time;
		this.responder = responder;
		this.reviewers = reviewers;
		this.content = content;
		this.isRead = isRead;
		this.likeNumber = likeNumber;
	}
	public Reply() {
		super();
	}
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public String getReply_time() {
		return reply_time;
	}
	public void setReply_time(String reply_time) {
		this.reply_time = reply_time;
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
	
	
}
