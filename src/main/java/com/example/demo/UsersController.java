package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

@Controller
public class UsersController {
	@Autowired
	private UsersService service;
	 
	@ResponseBody
	@RequestMapping("init")
	public String init() {
		Map<String, String> map=new HashMap<String, String>();
		List<User> list=service.lookForAllUser();
		for(int i=0;i<list.size();i++) {
			map.put(list.get(i).getUserId(), list.get(i).getEmail());
		}
		//连接本地的 Redis 服务    
    	Jedis jedis=new Jedis("localhost");        
        System.out.println("连接成功");       
        //查看服务是否运行	        
        System.out.println("服务正在运行: "+jedis.ping());
        jedis.hmset("users", map);
		jedis.close();
		//return list.get(0).getUserId()+" "+list.get(0).getBirthday()+" "+list.get(0).getGender()+" "+list.get(0).getEmail();
		return "录入全部用户信息于redis中";
	}
	
	@ResponseBody
	@RequestMapping("setout")
	public String setout() {
		return "setout";
	}
	
	@ResponseBody
	@RequestMapping("enroll")
	public String enroll(@RequestParam("userId") String userId,@RequestParam("birthday")String birthday,@RequestParam("gender")String gender,@RequestParam("email") String email) {
		User user=new User();
		user.setUserId(userId);
		user.setBirthday(birthday);
		user.setGender(gender);
		user.setEmail(email);
		String id=user.getUserId();
		Jedis jedis=new Jedis("localhost");        
        System.out.println("连接成功");      
        //return user.getUserId()+" "+user.getBirthday()+" "+user.getGender()+" "+user.getEmail();
        //查看服务是否运行	        
        System.out.println("服务正在运行: "+jedis.ping());
        System.out.println("aaa");
        if(jedis.hexists("users", id)) {        	
        	jedis.close();
        	System.out.println("账号已注册,在redis中查到。");
        	return "账号已注册,在redis中查到。";
        }
        jedis.close();
        System.out.println("bbb");
        if(service.lookForUser(id)) {
        	System.out.println("账号已注册，在MySQL中查到");
        	return "账号已注册，在MySQL中查到";
        }
        System.out.println("ccc");
		service.saveUser(user);
		System.out.println("注册成功");
		return "注册成功";
	}
}
