package com.cronberry.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cronberry.jwt.JwtFilter;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtFilter jwtfilter;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	
	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
        http.csrf().disable().authorizeRequests().antMatchers("/sign-in","/register")
        .permitAll().anyRequest().authenticated()
        .and().exceptionHandling().and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class).addFilterAfter(jwtfilter, UsernamePasswordAuthenticationFilter.class);;
		
	}

//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	AuthenticationManager authenticationManagerFactoryBean() throws Exception{
		return super.authenticationManagerBean();
	}

}
