package cn.virtualspider.blog.entity;

/**
 * 用户详情
 * @author virtualspider
 *
 */
public class UserDetail {
	private Long id;//用户id
	private String introduce;//个人介绍
	private String image;//个人图片
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
