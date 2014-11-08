package com.duoduo.jta.atomikos;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.duoduo.jta.atomikos.manager.ProductManager;
import com.duoduo.jta.atomikos.manager.UserManager;
import com.duoduo.jta.atomikos.model.Product;
import com.duoduo.jta.atomikos.model.User;
import com.duoduo.jta.atomikos.service.AtomikosService;

/**
 * JTA测试：Atomikos
 * @author chengesheng@gmail.com
 * @date 2014-11-8 下午8:05:56
 * @version 1.0.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AtomikosTest {

	private static ClassPathXmlApplicationContext context;
	private static UserManager userManager;
	private static ProductManager productManager;
	private static AtomikosService atomikosService;

	@BeforeClass
	public static void init() {
		context = new ClassPathXmlApplicationContext("applicationContext-atomikos.xml");
		userManager = context.getBean(UserManager.class);
		productManager = context.getBean(ProductManager.class);
		atomikosService = context.getBean(AtomikosService.class);
	}

	@AfterClass
	public static void close() {
		if (context != null) {
			context.close();
		}
	}

	@Test
	public void test10SaveUserAndProduct() {
		User user = new User();
		user.setName("user1");
		Product product = new Product();
		product.setName("product1");
		atomikosService.save(user, product);
	}

	@Test
	public void test20SaveProductAndUser() {
		User user = new User();
		user.setName("user2");
		Product product = new Product();
		product.setName("product2");
		atomikosService.save(product, user);
	}

	@Test
	public void test30SaveUserAndProductRollback() {
		try {
			User user = new User();
			user.setName("user3");
			Product product = new Product();
			atomikosService.save(user, product);
		} catch (Exception e) {
			int count = userManager.getCountByName("user3");
			Assert.assertEquals(0, count);
		}
	}

	@Test
	public void test40SaveUserAndProductRollback2() {
		try {
			User user = new User();
			Product product = new Product();
			product.setName("product4");
			atomikosService.save(user, product);
		} catch (Exception e) {
			int count = productManager.getCountByName("product4");
			Assert.assertEquals(0, count);
		}
	}

	@Test
	public void test50SaveProductAndUserRollback() {
		try {
			User user = new User();
			user.setName("user5");
			Product product = new Product();
			atomikosService.save(product, user);
		} catch (Exception e) {
			int count = userManager.getCountByName("user5");
			Assert.assertEquals(0, count);
		}
	}

	@Test
	public void test60SaveProductAndUserRollback2() {
		try {
			User user = new User();
			Product product = new Product();
			product.setName("product6");
			atomikosService.save(product, user);
		} catch (Exception e) {
			int count = productManager.getCountByName("product6");
			Assert.assertEquals(0, count);
		}
	}

}
