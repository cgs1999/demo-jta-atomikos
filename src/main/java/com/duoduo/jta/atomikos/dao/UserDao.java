package com.duoduo.jta.atomikos.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.duoduo.jta.atomikos.model.User;

@Repository
public class UserDao {

	@Resource(name = "db1JdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public void save(User user) {
		jdbcTemplate.update("insert into users(name) values(?)", user.getName());
	}

	public int getCountByName(String userName) {
		return (int) jdbcTemplate.queryForLong("select count(id) from users where name=?", userName);
	}

}
