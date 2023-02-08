package com.cronberry.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "user_jwt")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;
	
	@NotBlank(message="fill your name ")
	private String name;
	
	@NotBlank(message = "enter valid email")
	@Email
	@Column(unique = true)
	private String email;
	

	
//	@Size(min = 10,max = 10,message = "enter valid phone number")
//	@Range(min = 0,max = 10)
	@Pattern(regexp="(^$|[0-9]{10})")
	private String phonnumber;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dob;
	
	@Column(name = "invalidtoken")
	private String invalidToken;
	
	
	@Size(min = 4,message = "password is < 4 character")
	private String password;
	
	
	private String confirmpassword;
	
	private LocalDateTime userregistertime = LocalDateTime.now();
	
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@MapsId("roleid")
	private Set<Role> roles=new HashSet<>();


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(Long userid, @NotBlank(message = "fill your name ") String name,
			@NotBlank(message = "enter valid email") @Email String email,
			@Pattern(regexp = "(^$|[0-9]{10})") String phonnumber, LocalDate dob, String invalidToken,
			@Size(min = 4, message = "password is < 4 character") String password, String confirmpassword,
			LocalDateTime userregistertime, Set<Role> roles) {
		super();
		this.userid = userid;
		this.name = name;
		this.email = email;
		this.phonnumber = phonnumber;
		this.dob = dob;
		this.invalidToken = invalidToken;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.userregistertime = userregistertime;
		this.roles = roles;
	}


	public Long getUserid() {
		return userid;
	}


	public void setUserid(Long userid) {
		this.userid = userid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhonnumber() {
		return phonnumber;
	}


	public void setPhonnumber(String phonnumber) {
		this.phonnumber = phonnumber;
	}


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


	public LocalDateTime getUserregistertime() {
		return userregistertime;
	}


	public void setUserregistertime(LocalDateTime userregistertime) {
		this.userregistertime = userregistertime;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	


	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getInvalidToken() {
		return invalidToken;
	}


	public void setInvalidToken(String invalidToken) {
		this.invalidToken = invalidToken;
	}
	
	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}


	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", email=" + email + ", phonnumber=" + phonnumber
				+ ", dob=" + dob + ", userregistertime=" + userregistertime + ", roles=" + roles + "]";
	}


}
