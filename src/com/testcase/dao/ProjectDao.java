package com.testcase.dao;

import java.util.List;

import com.testcase.model.Project;

public interface ProjectDao {

	Project findById(int id);

	void save(Project project);
	
	List<Project> getAllData();
}
