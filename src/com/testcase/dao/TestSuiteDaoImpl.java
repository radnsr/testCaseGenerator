package com.testcase.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.testcase.model.Project;
import com.testcase.model.TestSuite;

@Repository("testSuitDao")
public class TestSuiteDaoImpl extends AbstractDao<Integer, TestSuite> implements TestSuiteDao {

	@Override
	public TestSuite findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public void save(TestSuite testSuite) {
		persist(testSuite);
	}

	@Override
	public List<TestSuite> getAllData() {
		Criteria criteria = createEntityCriteria();
		return (List<TestSuite>) criteria.list();
	}

}
