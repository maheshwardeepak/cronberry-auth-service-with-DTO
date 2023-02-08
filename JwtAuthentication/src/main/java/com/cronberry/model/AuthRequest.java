package com.cronberry.model;

import javax.validation.constraints.Email;

public class AuthRequest {
	
	@Email
	private String username;
	private String password;
	
	
	public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public AuthRequest(@Email String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}



	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	

}
