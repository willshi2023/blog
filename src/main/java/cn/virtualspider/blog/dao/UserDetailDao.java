package cn.virtualspider.blog.dao;

import org.apache.ibatis.annotations.Select;

import cn.virtualspider.blog.entity.UserDetail;

public interface UserDetailDao {
	/**
	 * 通过用户id获取用户信息
	 * @param userId
	 * @return
	 */
	@Select("select * from user_dtl where id=#{userId}")
	public UserDetail getUserIntroduceByUserId(Long userId);
	
}
