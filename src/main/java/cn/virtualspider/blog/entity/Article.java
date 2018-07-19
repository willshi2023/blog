package cn.virtualspider.blog.entity;

/**
 * 文章的实体类
 * @author Administrator
 *
 */
public class Article {
	private Long id;						//文章id
	private String title;					//标题
	private String summary;					//摘要
	private String show_pictrue;			//展示图片路径
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getShow_pictrue() {
		return show_pictrue;
	}
	public void setShow_pictrue(String show_pictrue) {
		this.show_pictrue = show_pictrue;
	}
	
}
