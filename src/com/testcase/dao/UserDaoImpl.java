package com.testcase.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.testcase.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User findById(int id) {
		return getByKey(id);
	}

	public void save(User user) {
		persist(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllData() {
		Criteria criteria = createEntityCriteria();
		return (List<User>) criteria.list();
	}

}
