package com.cronberry.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cronberry.model.User;
import com.cronberry.repository.UserRepository;

@Service
public class CustomUserDetailService  implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repo.findByEmail(username);
		CustomUserDetail userdetail =null; 
		
		if(user != null) {
			
			userdetail = new CustomUserDetail();
			userdetail.setUser(user);  
			
		}else {
			throw new UsernameNotFoundException("user does not exist : "+username);
		}
		
		return userdetail;
	}

}
