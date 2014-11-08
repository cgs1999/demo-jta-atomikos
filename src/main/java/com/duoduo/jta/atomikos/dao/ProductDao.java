package com.duoduo.jta.atomikos.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.duoduo.jta.atomikos.model.Product;

@Repository
public class ProductDao {

	@Resource(name = "db2JdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public void save(Product product) {
		jdbcTemplate.update("insert into product(name) values(?)", product.getName());
	}

	public int getCountByName(String productName) {
		return (int) jdbcTemplate.queryForLong("select count(id) from product where name=?", productName);
	}

}
