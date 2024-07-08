package com.example.to.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.to.entity.User;

@Mapper
public interface UserDao {
	@Select("select * from users where uid=#{uid}")
	User getUser(String uid);
	
	@Insert("insert into users values (#{uid}, #{pwd}, #{name}, #{type})")
	void insertUser(User user);
	
}