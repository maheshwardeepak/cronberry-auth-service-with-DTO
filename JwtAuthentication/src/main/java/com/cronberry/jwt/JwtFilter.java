package com.cronberry.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cronberry.model.User;
import com.cronberry.repository.UserRepository;
import com.cronberry.security.CustomUserDetailService;

@Component
public class JwtFilter  extends OncePerRequestFilter{
	
	@Autowired
	private JwtHelpar jwthelpar;
	
	@Autowired
	private CustomUserDetailService service;
	
	@Autowired
	private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

    	
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!++++++++++++++++++++++++++++++++++++++++++++" +httpServletRequest.getHeader("Authorization"));
    	
//    	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@=========================################"+httpServletResponse.getHeader(ALREADY_FILTERED_SUFFIX));
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String token = null;
        String userName = null;
        

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            userName = jwthelpar.extractUsername(token);
        }
        
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        	User existUser = userRepository.findByEmail(userName);
        	
        	if(existUser.getInvalidToken() !=null && existUser.getInvalidToken().equals(token)) {
        		httpServletResponse.setHeader("abc", "def");
        		userName = null;
        		
        		System.out.println(httpServletRequest.getHeader("---------------------------"+"Authorization"));
        		System.out.println(httpServletResponse.getHeader("=========================="+"Authorization"));
        	}
        
        }


        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = service.loadUserByUsername(userName);

            if (jwthelpar.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
