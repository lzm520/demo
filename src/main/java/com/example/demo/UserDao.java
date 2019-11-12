package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	
	boolean lookForUser(String userId);
	
	List<User> lookForAllUser();
	
	void saveUser(User user);
}
