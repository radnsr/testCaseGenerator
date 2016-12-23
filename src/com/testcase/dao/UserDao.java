package com.testcase.dao;

import java.util.List;

import com.testcase.model.User;

public interface UserDao {

	User findById(int id);

	void save(User user);
	
	List<User> getAllData();

	User findByEmail(String email);

}
