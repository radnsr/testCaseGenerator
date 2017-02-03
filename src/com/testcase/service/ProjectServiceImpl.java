package com.testcase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testcase.dao.ProjectDao;
import com.testcase.model.Project;
import com.testcase.model.User;

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService{
	@Autowired
	private ProjectDao dao;
	
	@Override
	public Project findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public void save(Project project) {
dao.save(project);		
	}

	@Override
	public void update(Project project) {
		Project entity = dao.findById(project.getId());
		if(entity!=null){
			entity.setProjectName(project.getProjectName());
			entity.setFunc_require(project.getFunc_require());
			entity.setNon_func_require(project.getNon_func_require());
			entity.setStatus(project.getStatus());
		}		
	}

	@Override
	public List<Project> getAllData() {
		// TODO Auto-generated method stub
		return dao.getAllData();
	}

}
