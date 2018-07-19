package cn.virtualspider.blog.entity;

import java.util.Date;
import java.util.List;

/**
 * 用户表
 * @author virtualspider
 *
 */
public class User {
	private Long id;//用户id
	private String username;//姓名
	private String password;//密码
	private Date registTime;//注册时间
	private List<Role> roles;//拥有的角色
	private String status;//账户信息，是否可用
	private Date lastLoginTime;//最后一次登录日期
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


}
