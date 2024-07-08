package com.example.to.service;

import com.example.to.entity.User;

public interface UserService {
	public static final int CORRECT_LOGIN = 0;
	public static final int WRONG_PASSWORD = 1;
	public static final int USER_NOT_EXIST = 2;
	public static final int COUNT_PER_PAGE = 10;

	User getUserByUid(String uid);
	
//	void registerUser(User user);
	
	int login(String uid, String pwd);
}