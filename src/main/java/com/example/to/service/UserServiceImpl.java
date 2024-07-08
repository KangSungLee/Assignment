package com.example.to.service;

import org.springframework.stereotype.Service;

import com.example.to.dao.UserDao;
import com.example.to.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserDao uDao;
	
	@Override
	public User getUserByUid(String uid) {
		return uDao.getUser(uid); 
	}

	@Override
    public int login(String uid, String pwd) {
        User user = uDao.getUser(uid);
        if (user == null)
            return UserService.USER_NOT_EXIST;

        if (pwd.equals(user.getPwd())) {
            return UserService.CORRECT_LOGIN;
        } else {
            return UserService.WRONG_PASSWORD;
        }
    }

}