//package com.sqa.bhxh.services.impl;
//
//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.sqa.bhxh.dao.UserDao;
//import com.sqa.bhxh.entities.User;
//import com.sqa.bhxh.services.UserService;
//
//@Service(value = "userService")
//public class UserServiceImpl implements UserDetailsService, UserService{
//
//	@Autowired
//	private UserDao userDao;
//
//	@Override
//	public boolean authenUser(String username, String password) {
//		// TODO Auto-generated method stub
//		return userDao.authenUser(username, password);
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		User user = userDao.getUserByUserName(username);
//		if(user == null){
//			throw new UsernameNotFoundException("Invalid username or password.");
//		}
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
//	}
//
//	private List<SimpleGrantedAuthority> getAuthority(User user) {
//		return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
//	}
//
//}
