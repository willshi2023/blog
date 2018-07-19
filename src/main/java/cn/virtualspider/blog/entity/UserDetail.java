package cn.virtualspider.blog.entity;

/**
 * 用户详情
 * @author virtualspider
 *
 */
public class UserDetail {
	private Long id;//用户id
	private String introduce;//个人介绍
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
}
