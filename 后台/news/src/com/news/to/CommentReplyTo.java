package com.news.to;

import java.util.ArrayList;

import com.news.entity.Comment;
import com.news.entity.Reply;

public class CommentReplyTo {
	private Comment comment;//评论
	private ArrayList<Reply> replys;//该评论的回复
	private String comment_head_portrait_url;//评论头像
	private ArrayList<String> replys_head_portrait_url;//回复头像
	@Override
	public String toString() {
		return "CommentReplyTo [comment=" + comment + ", replys=" + replys + ", comment_head_portrait_url="
				+ comment_head_portrait_url + ", replys_head_portrait_url=" + replys_head_portrait_url + "]";
	}
	public CommentReplyTo(Comment comment, ArrayList<Reply> replys, String comment_head_portrait_url,
			ArrayList<String> replys_head_portrait_url) {
		super();
		this.comment = comment;
		this.replys = replys;
		this.comment_head_portrait_url = comment_head_portrait_url;
		this.replys_head_portrait_url = replys_head_portrait_url;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public ArrayList<Reply> getReplys() {
		return replys;
	}
	public void setReplys(ArrayList<Reply> replys) {
		this.replys = replys;
	}
	public String getComment_head_portrait_url() {
		return comment_head_portrait_url;
	}
	public void setComment_head_portrait_url(String comment_head_portrait_url) {
		this.comment_head_portrait_url = comment_head_portrait_url;
	}
	public ArrayList<String> getReplys_head_portrait_url() {
		return replys_head_portrait_url;
	}
	public void setReplys_head_portrait_url(ArrayList<String> replys_head_portrait_url) {
		this.replys_head_portrait_url = replys_head_portrait_url;
	}
	
}
