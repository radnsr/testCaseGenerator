package com.testcase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testcase.dao.TestSuiteDao;
import com.testcase.model.TestSuite;

@Service("testSuiteService")
@Transactional
public class TestSuiteServiceImpl implements TestSuiteService {
	@Autowired
	private TestSuiteDao dao;
	
	@Override
	public TestSuite findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void save(TestSuite testSuite) {
			dao.save(testSuite);
	}

	@Override
	public void update(TestSuite testSuite) {
		TestSuite entity= dao.findById(testSuite.getId());
		
		if(entity != null){
			entity.setProject(testSuite.getProject());
			entity.setTest_suit_name(testSuite.getTest_suit_name());
			entity.setStatus(testSuite.getStatus());
		}
	}

	@Override
	public List<TestSuite> getAllData() {
		// TODO Auto-generated method stub
		return dao.getAllData();
	}

}
