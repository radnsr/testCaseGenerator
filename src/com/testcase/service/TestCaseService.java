package com.testcase.service;

import java.util.ArrayList;
import java.util.List;

import com.testcase.model.TestCase;

public interface TestCaseService {
	TestCase findById(int id);
	
	void save(TestCase testCase);
	
	void update(TestCase testCase);
	

	List<TestCase> getAllData();
}
