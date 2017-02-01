package com.testcase.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.testcase.model.TestCase;
import com.testcase.model.User;

@Repository("testCaseDao")
public class TestCaseDaoImpl extends AbstractDao<Integer, TestCase> implements TestCaseDao {

	@Override
	public TestCase findById(int id) {
		return null;
	}

	@Override
	public void save(TestCase testSuite) {
	}

	@Override
	public List<TestCase> getAllData() {
		return null;
	}

}
