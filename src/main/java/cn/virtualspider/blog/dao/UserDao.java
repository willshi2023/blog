package cn.virtualspider.blog.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.virtualspider.blog.entity.User;

public interface UserDao {
	@Select("select * from user where username = #{username}")
	User findUserByUserName(@Param("username")String username);
	
	@Insert("insert user(username,password,registTime,status,lastLoginTime) "
			+ "values (#{username},#{password},#{registTime},#{status},#{lastLoginTime})")
	void InsertUser(User user);

	@Select("select * from user where username = #{username} and password=#{password}")
	User findUserByUserNameAndPassword(@Param("username")String username, @Param("password")String password);
}