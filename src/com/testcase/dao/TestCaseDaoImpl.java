package com.testcase.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.testcase.model.Project;
import com.testcase.model.TestCase;
import com.testcase.model.User;

@Repository("testCaseDao")
public class TestCaseDaoImpl extends AbstractDao<Integer, TestCase> implements TestCaseDao {

	@Override
	public TestCase findById(int id) {
		return getByKey(id);
	}

	@Override
	public void save(TestCase testSuite) {
		persist(testSuite);
	}

	@Override
	public List<TestCase> getAllData() {
		Criteria criteria = createEntityCriteria();
		return (List<TestCase>) criteria.list();		}

}
