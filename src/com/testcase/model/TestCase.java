package com.testcase.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TestCase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "TEST_SUIT_ID")
	private int test_suit_id;
	
	@Column(name = "PROJECT_ID")
	private int projetc_id;
	
	@Column(name = "FUNC_REQUIRE")
	private String func_require;
	
	@Column(name = "NON_FUNC_REQUIRE")
	private String non_func_require;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTest_suit_id() {
		return test_suit_id;
	}

	public void setTest_suit_id(int test_suit_id) {
		this.test_suit_id = test_suit_id;
	}

	public int getProjetc_id() {
		return projetc_id;
	}

	public void setProjetc_id(int projetc_id) {
		this.projetc_id = projetc_id;
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
	
}
