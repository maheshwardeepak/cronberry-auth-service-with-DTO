package com.cronberry.model.service;

import java.util.List;

import com.cronberry.model.User;

public interface UserService {
	
	
	//User create
	
	User createuser(User user);
	
//	show users
	
	List<User> showallusers();
	
	
//	find byid user
	
	User showByiduser(Long userid);
	
//	delete user by id
	
	void deleteuserbyid(Long userid);
	
//	update user by id
	
	User updateuserbyid(User user,Long userid);
	
	
//	find by email

	User updateuserPasswordbyEmail(User user,String email);

}
