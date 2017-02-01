package com.testcase.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testcase.model.TestSuite;

@Service("testSuiteService")
@Transactional
public class TestSuiteServiceImpl implements TestSuiteService {

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
	public void update(TestSuite testSuite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TestSuite> getAllData() {
		// TODO Auto-generated method stub
		return null;
	}

}
