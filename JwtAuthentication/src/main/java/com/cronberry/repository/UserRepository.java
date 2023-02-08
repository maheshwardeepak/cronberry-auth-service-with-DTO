package com.cronberry.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cronberry.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	  User findByEmail(String Email);
	  
	  @Transactional
	  @Modifying
	  @Query(value = "update user_jwt u SET u.password = :password, u.invalidtoken = :invalidToken WHERE u.email= :userName ;",nativeQuery = true)
	  void updatePassword(String userName, String password,String invalidToken); 

}
