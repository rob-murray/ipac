package com.ipac.app.service.impl;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ipac.app.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	   
    public String getCurrentUsername(){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); //get logged in username         

    }	

}
