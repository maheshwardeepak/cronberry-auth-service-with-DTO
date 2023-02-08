package com.cronberry.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cronberry.jwt.JwtHelpar;
import com.cronberry.model.AuthRequest;
import com.cronberry.model.User;
import com.cronberry.model.service.UserServiceimpl;
import com.cronberry.repository.UserRepository;

@RestController
public class TokenGenerateController {
	@Autowired
	private UserServiceimpl service;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtHelpar jwtHelpar;

	@Autowired
	private UserRepository repo;

	@PostMapping("/register")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		return new ResponseEntity<User>(service.createuser(user), HttpStatus.CREATED);
	}

	@PostMapping("/sign-in")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getusername(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		return jwtHelpar.generateToken(authRequest.getusername());
	}

	@PatchMapping("/passwordchange/{email}")
	public String passwordChange(@RequestBody User user, @PathVariable String email,
			@RequestHeader("Authorization") String token) throws Exception {

//    	service.updateuserPasswordbyEmail(user, email);
//    	AuthRequest authRequest = new AuthRequest();
//    	authRequest.setusername(email);
//    	authRequest.setPassword(user.getPassword());
		
		
		token = token.substring(7);
		repo.updatePassword(email, user.getPassword(), token);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!" + token);

//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getusername(), authRequest.getPassword())
//            );
//        } catch (Exception ex) {
//            throw new Exception("inavalid username/password");
//        }
//        String tokenjwt2=jwtHelpar.generateToken(authRequest.getusername()); 

		return "password change :" + email;
	}

}
