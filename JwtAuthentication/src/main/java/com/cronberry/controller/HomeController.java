package com.cronberry.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronberry.model.User;
import com.cronberry.model.service.UserServiceimpl;



@RestController
@RequestMapping("/cronberry")
public class HomeController {
	
	@Autowired
	private UserServiceimpl service;
	
	
//	@PreAuthorize("SUPER_ADMIN")
	@PostMapping
	public ResponseEntity<User> createUser(@Valid  @RequestBody User user){
		return new ResponseEntity<User>(service.createuser(user), HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<User>> showAllUserList(){
		return new ResponseEntity<List<User>>(service.showallusers(), HttpStatus.OK);
		
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<User> showUserById(@PathVariable Long userid){
		return new ResponseEntity<User>(service.showByiduser(userid), HttpStatus.OK);
	}
	
	@DeleteMapping("/{userid}")
	public ResponseEntity<String> deleteUserById(@PathVariable Long userid){
		service.deleteuserbyid(userid);
		return new ResponseEntity<String>("delete successfully user : "+userid, HttpStatus.OK);
	}
	@PutMapping("/{userid}")
	public ResponseEntity<User> updateUserById(@Valid   @RequestBody User user,@PathVariable Long userid){
		return new ResponseEntity<User>(service.updateuserbyid(user, userid),HttpStatus.OK);
	}
	
	



}
