package com.testcase.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.testcase.model.Project;
import com.testcase.model.User;

@Repository("projectDao")
public class ProjectDaoImpl extends AbstractDao<Integer, Project> implements ProjectDao{

	@Override
	public Project findById(int id) {
		return getByKey(id);
	}

	@Override
	public void save(Project project) {
		persist(project);	
	}

	@Override
	public List<Project> getAllData() {
		Criteria criteria = createEntityCriteria();
		return (List<Project>) criteria.list();	
	}

}
