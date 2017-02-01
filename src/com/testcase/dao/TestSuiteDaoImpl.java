package com.testcase.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.testcase.model.TestSuite;

@Repository("testSuitDao")
public class TestSuiteDaoImpl extends AbstractDao<Integer, TestSuite> implements TestSuiteDao {

	@Override
	public TestSuite findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(TestSuite testSuite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TestSuite> getAllData() {
		// TODO Auto-generated method stub
		return null;
	}

}
