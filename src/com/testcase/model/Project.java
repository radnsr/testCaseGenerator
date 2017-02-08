package com.testcase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")
	private int id;
	
	@Column(name = "PROJECT_NAME")
	private String projectName;
	
	@Column(name = "FUNC_REQUIRE",length=1000)
	private String func_require;
	
	@Column(name = "NON_FUNC_REQUIRE",length=1000)
	private String non_func_require;
	
	@Column(name = "STATUS")
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	
	public String getFunc_require() {
		return func_require;
	}

	public void setFunc_require(String func_require) {
		this.func_require = func_require;
	}

	public String getNon_func_require() {
		return non_func_require;
	}

	public void setNon_func_require(String non_func_require) {
		this.non_func_require = non_func_require;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
