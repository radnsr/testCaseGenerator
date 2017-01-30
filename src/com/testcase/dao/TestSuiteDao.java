package com.testcase.dao;

import java.util.List;

import com.testcase.model.TestSuite;


public interface TestSuiteDao {


	TestSuite findById(int id);

	void save(TestSuite testSuite);
	
	List<TestSuite> getAllData();
}
