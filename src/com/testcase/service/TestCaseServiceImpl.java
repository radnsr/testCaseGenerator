package com.testcase.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testcase.model.TestCase;

@Service("testCaseService")
@Transactional
public class TestCaseServiceImpl implements TestCaseService{

	@Override
	public TestCase findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(TestCase testCase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TestCase testCase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TestCase> getAllData() {
		// TODO Auto-generated method stub
		return null;
	}

}
