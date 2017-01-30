package com.testcase.service;

import java.util.List;

import com.testcase.model.Project;

public interface ProjectService {
	Project findById(int id);
	
	void save(Project project);
	
	void update(Project project);
	

	List<Project> getAllData();
}
