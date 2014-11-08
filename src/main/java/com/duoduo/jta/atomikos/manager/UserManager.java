package com.duoduo.jta.atomikos.manager;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duoduo.jta.atomikos.dao.UserDao;
import com.duoduo.jta.atomikos.model.User;

@Service
@Transactional
public class UserManager {

	@Resource
	private UserDao userDao;

	public void save(User user) {
		userDao.save(user);
	}

	public int getCountByName(String userName) {
		return userDao.getCountByName(userName);
	}

}
