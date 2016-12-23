package com.testcase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testcase.dao.UserDao;
import com.testcase.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	public User findById(int id) {
		return dao.findById(id);
	}

	public void save(User user) {
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void update(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setEmail(user.getEmail());
			entity.setPassword(user.getPassword());
			entity.setFirst_name(user.getFirst_name());
			
		}
	}

	
	
	public List<User> getAllData() {
		return dao.getAllData();
	}

	public User authenticate(User user) {
		User valid_user=findByEmail(user.getEmail());
		
			if (valid_user.getPassword().equals(user.getPassword())) {

				return valid_user;
			}
		
		return null;
	}
	@Override
	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}
	
}
