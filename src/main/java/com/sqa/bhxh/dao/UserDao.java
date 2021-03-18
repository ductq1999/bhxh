package com.sqa.bhxh.dao;

import com.sqa.bhxh.entities.User;

public interface UserDao {

	boolean authenUser(String username, String password);
	
	User getUserByUserName(String userName);
}
