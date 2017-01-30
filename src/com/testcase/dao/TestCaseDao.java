package com.testcase.dao;

import java.util.List;

import com.testcase.model.TestCase;

public interface TestCaseDao {


	TestCase findById(int id);

	void save(TestCase testSuite);
	
	List<TestCase> getAllData();
}
