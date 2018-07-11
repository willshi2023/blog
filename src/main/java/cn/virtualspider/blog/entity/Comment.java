package cn.virtualspider.blog.entity;

import java.util.Date;

/**
 * 评论类
 * @author virtualspider
 *
 */
public class Comment {
	private String blogId;//博客id
	private int userId;//用户id
	private String comments;//用户评论
	private Date time;//评论时间
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
}
