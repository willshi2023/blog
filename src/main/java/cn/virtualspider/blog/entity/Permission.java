package cn.virtualspider.blog.entity;

/**
 * 权限表
 * @author virtualspider
 *
 */
public class Permission {
	private int permissionId;//权限id
	private String permissionName;//权限名
	public int getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	
}
