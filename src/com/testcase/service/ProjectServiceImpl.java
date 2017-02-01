package com.testcase.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testcase.model.Project;

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService{

	@Override
	public Project findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Project project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Project project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Project> getAllData() {
		// TODO Auto-generated method stub
		return null;
	}

}
