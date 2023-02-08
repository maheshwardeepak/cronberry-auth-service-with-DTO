package com.cronberry.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cronberry.model.User;
import com.cronberry.repository.UserRepository;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	private UserRepository repo;

//	@Autowired
//	private PasswordEncoder encoder;

	@Override
	public User createuser(User user) {
		// TODO Auto-generated method stub
//		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}

	@Override
	public List<User> showallusers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public User showByiduser(Long userid) {
		// TODO Auto-generated method stub
		return repo.findById(userid).orElse(null);
	}

	@Override
	public void deleteuserbyid(Long userid) {
		// TODO Auto-generated method stub
		repo.deleteById(userid);

	}

	@Override
	public User updateuserbyid(User user, Long userid) {
		// TODO Auto-generated method stub
		return repo.findById(userid).map(users -> {
			users.setName(user.getName());
			users.setEmail(user.getEmail());
//			users.setPassword(encoder.encode(user.getPassword()));
			users.setPassword(user.getPassword());
			users.setPhonnumber(user.getPhonnumber());
			users.setDob(user.getDob());
			users.setUserregistertime(user.getUserregistertime());
			users.setRoles(user.getRoles());
			return repo.save(users);

		}).orElseGet(() -> {
			user.setUserid(userid);
			return repo.save(user);
		});
	}
	
	
	
	public User updateuserPasswordbyEmail(User user,String email) {
	
		
		User userbyemail = repo.findByEmail(email);
		if(userbyemail != null) {
		
		userbyemail.setPassword(user.getPassword());
		return repo.save(userbyemail);

		}else {
			throw new UsernameNotFoundException("this email user does not exist");
		}
		
		
	}

}
