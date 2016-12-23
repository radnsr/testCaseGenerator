package com.testcase.service;

import java.util.List;

import com.testcase.model.User;

public interface UserService {

	User findById(int id);
	
	void save(User user);
	
	void update(User user);
	

	List<User> getAllData();

	User findByEmail(String email); 
	
	public User authenticate(User user);	
}
