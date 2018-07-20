package cn.virtualspider.blog.entity;

import java.util.Date;
import java.util.List;

/**
 * 文章详情的实体类
 * @author Administrator
 *
 */
public class ArticleDetail {
	private Long id;						//文章id
	private String title;					//标题
	private String username;				//作者
	private Date createTime;				//创作时间
	private Long readCount;					//阅读数
	private String summary;					//摘要
	private List<String> tags;				//标签
	private String content;					//文章内容
	private Long articleId;					//文章id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getReadCount() {
		return readCount;
	}
	public void setReadCount(Long readCount) {
		this.readCount = readCount;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	
}
