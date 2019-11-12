package com.example.demo;

import java.util.List;

public interface UsersService {
	
	boolean lookForUser(String userId);
	
	List<User> lookForAllUser();
	
	void saveUser(User user);
}
