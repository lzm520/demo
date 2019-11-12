package com.example.demo;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 3537921742065870581L;
	
	/**
	 * 用户ID，生日，性别，邮箱
	 */
	private String userId,birthday,gender,email;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
