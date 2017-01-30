package com.testcase.service;

import java.util.List;

import com.testcase.model.TestSuite;

public interface TestSuiteService {
	TestSuite findById(int id);
	
	void save(TestSuite testSuite);
	
	void update(TestSuite testSuite);
	

	List<TestSuite> getAllData();
}
