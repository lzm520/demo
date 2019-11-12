package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value = "userService")
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UserDao userdao;
	
	@Override
	public boolean lookForUser(String userId) {
		// TODO Auto-generated method stub
		return userdao.lookForUser(userId);
	}
	@Override
	public List<User> lookForAllUser() {
		// TODO Auto-generated method stub
		return userdao.lookForAllUser();
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userdao.saveUser(user);
	}

}
