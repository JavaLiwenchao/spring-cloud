package com.lwc;

import com.lwc.dao.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJtaApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private RedisDao redisDao;

	@Test
	public void redisTest() {

		redisDao.setKey("name", "Bruce");
		redisDao.setKey("password", "123443433");
		String name = redisDao.getValue("name");
		System.out.println(name);
		String password = redisDao.getValue("password");
		System.out.println(password);
	}

}
