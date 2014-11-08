package com.duoduo.jta.atomikos.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duoduo.jta.atomikos.manager.ProductManager;
import com.duoduo.jta.atomikos.manager.UserManager;
import com.duoduo.jta.atomikos.model.Product;
import com.duoduo.jta.atomikos.model.User;

@Service
@Transactional
public class AtomikosService {

	@Resource
	private UserManager userManager;

	@Resource
	private ProductManager productManager;

	public void save(User user, Product product) {
		userManager.save(user);
		productManager.save(product);
	}

	public void save(Product product, User user) {
		productManager.save(product);
		userManager.save(user);
	}

}
