package com.empathy.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empathy.pojo.User;
import com.empathy.utils.JsonUtils;
import com.empathy.utils.LeeJSONResult;
import com.empathy.utils.RedisOperator;

@RestController
@RequestMapping("redis")
public class RedisController {
	
	@Autowired
	private StringRedisTemplate strRedis;
	
	@Autowired
	private RedisOperator redis;

	@RequestMapping("/test")
	public LeeJSONResult test() {
		
		strRedis.opsForValue().set("test-StringRedisTemplate", "hello StringRedisTemplate~~~~~~");
		
		return LeeJSONResult.ok(strRedis.opsForValue().get("test-StringRedisTemplate"));
	}

	@RequestMapping("/test2")
	public LeeJSONResult test2() {
		
		redis.set("use-jedis-in-springboot", "hello jedis operator~~~", 1000);
		
		String value = redis.get("use-jedis-in-springboot");
		long expire = redis.ttl("use-jedis-in-springboot");
		
		return LeeJSONResult.ok("jedis中的值为：" + value + ", 剩余时间ttl为：" + expire);
	}
	
	@RequestMapping("/getJsonObject")
	public LeeJSONResult getJsonObject() {
		
		User user = new User();
		user.setAge(18);
		user.setName("lee");
		user.setPassword("123456");
		user.setBirthday(new Date());
		
		redis.set("json:info:user", JsonUtils.objectToJson(user), 2000);
		
		String userJson = redis.get("json:info:user");
		User userBorn = JsonUtils.jsonToPojo(userJson, User.class);
		
		return LeeJSONResult.ok(userBorn);
	}
	
	@RequestMapping("/getJsonList")
	public LeeJSONResult getJsonList() {
		
		User user = new User();
		user.setAge(18);
		user.setName("lee");
		user.setPassword("123456");
		user.setBirthday(new Date());
		
		User u1 = new User();
		u1.setAge(19);
		u1.setName("itzixi");
		u1.setPassword("123456");
		u1.setBirthday(new Date());
		
		User u2 = new User();
		u2.setAge(17);
		u2.setName("LeeCX");
		u2.setPassword("123456");
		u2.setBirthday(new Date());
		
		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(u1);
		userList.add(u2);
		
		
		redis.set("json:info:userlist", JsonUtils.objectToJson(userList), 2000);
		
		String userListJson = redis.get("json:info:userlist");
		List<User> userListBorn = JsonUtils.jsonToList(userListJson, User.class);
		
		return LeeJSONResult.ok(userListBorn);
	}
}