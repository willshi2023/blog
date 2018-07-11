package cn.virtualspider.blog.entity;

import java.util.Date;
import java.util.List;

/**
 * 博客的实体类
 * @author virtualspider
 *
 */
public class Blog {
	private int blogId;//博客id
	private String username;//用户名
	private String title;//博客标题
	private String content;//博客内容
	private String summary;//内容摘要
	private Date time;//博客时间
	private int readCount;//阅读数
	private List<Comment> comments;//评论
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Blog() {//自动实现提交时间和阅读数的初始化
		time = new Date();
		readCount = 0;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
}
