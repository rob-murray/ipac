
package com.ipac.app.service;


/**
 * Simple service for processing Users
 * @author rmurray
 */
public interface UserService {
    
    /**
    * Retrieves Current Logged in Username as string
    *
    * @return The Username of the logged in user
    */    
    public String getCurrentUsername();
    
}
