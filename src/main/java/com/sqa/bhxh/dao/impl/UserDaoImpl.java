package com.sqa.bhxh.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sqa.bhxh.dao.UserDao;
import com.sqa.bhxh.entities.User;

@Transactional
@Repository
public class UserDaoImpl implements UserDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean authenUser(String username, String password) {
		// TODO Auto-generated method stub
		String hql = "FROM User as u WHERE u.username = :username AND u.password = :password AND u.activeFlg = 1 AND u.isDeleted = 0";
		List<User> lstUsers = (List<User>) entityManager.createQuery(hql).setParameter("username", username).setParameter("password", password).getResultList();
		if(lstUsers != null && lstUsers.size()>0) {
			return true;
		}
		return false;
	}

	@Override
	public User getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		String hql = "FROM User as u WHERE u.username = :username AND u.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<User> lstResult = entityManager.createQuery(hql).setParameter("username", userName).getResultList();
		if (lstResult != null && lstResult.size() > 0) {
			return lstResult.get(0);
		}
		return null;
	}

}
