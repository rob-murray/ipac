
package com.ipac.app.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Simple service for processing Users
 * @author rmurray
 */
@Service("userService")
public class UserService {
    
    /**
    * Retrieves Current Logged in Username as string
    *
    * @return string username
    */    
    public String getCurrentUsername(){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); //get logged in username         

    }
    
}
