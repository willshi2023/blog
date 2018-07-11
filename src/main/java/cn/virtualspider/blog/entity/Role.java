package cn.virtualspider.blog.entity;

import java.util.List;

/**
 * 角色表
 * @author virtualspider
 *
 */
public class Role {
	private int roleId;//角色id
	private String roleName;//角色名
	private List<Permission> permissions;//拥有的权限
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
