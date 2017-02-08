package com.testcase.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testcase.dao.TestCaseDao;
import com.testcase.model.TestCase;

@Service("testCaseService")
@Transactional
public class TestCaseServiceImpl implements TestCaseService{
	@Autowired
	private TestCaseDao dao;
	
	@Override
	public TestCase findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void save(TestCase testCase) {
		save(testCase);
	}

	@Override
	public void update(TestCase testCase) {
TestCase entity= dao.findById(testCase.getId());
if(entity !=null){
	entity.setFunc_require(testCase.getFunc_require());
	entity.setNon_func_require(testCase.getNon_func_require());
	entity.setProjetc_id(testCase.getProjetc_id());
	entity.setTest_suit_id(testCase.getTest_suit_id());
}
		
	}

	@Override
	public List<TestCase> getAllData() {
		// TODO Auto-generated method stub
		return dao.getAllData();
	}



}
