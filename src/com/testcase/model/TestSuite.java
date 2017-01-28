package com.testcase.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TestSuite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "PROJECT_ID")
	private int project_id;
	
	@Column(name = "TEST_SUIT_NAME")
	private String test_suit_name;
	
	@Column(name = "STATUS")
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getTest_suit_name() {
		return test_suit_name;
	}

	public void setTest_suit_name(String test_suit_name) {
		this.test_suit_name = test_suit_name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
