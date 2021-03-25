package com.sqa.bhxh.dao.impl;

import com.sqa.bhxh.dao.UserDao;
import com.sqa.bhxh.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao{

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public boolean authenUser(String username, String password) {
		// TODO Auto-generated method stub
		String hql = "FROM User AS u WHERE u.username = :username AND u.password = :password AND u.activeFlg = 1 AND u.isDeleted = 0";
		List<User> lstUsers = (List<User>) entityManager.createQuery(hql).setParameter("username", username).setParameter("password", password).getResultList();
		return lstUsers != null && lstUsers.size() > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		String hql = "FROM User AS u WHERE u.username = :username AND u.isDeleted=0 AND u.activeFlg = 1";
		List<User> lstResult = entityManager.createQuery(hql).setParameter("username", userName).getResultList();
		if (lstResult != null && lstResult.size() > 0) {
			return lstResult.get(0);
		}
		return null;
	}

}
