package com.ipac.app.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.ipac.app.service.UserService;


/**
 * IpacWebController default webcontroller for application.
 * Extend this Class to utilise common methods and attributes
 * 
 * @author rmurray
 *
 */
abstract class IpacWebController {
	
	protected static Logger logger = Logger.getLogger("controller");
	
    @Resource(name="userService")
	protected UserService userService;

    /**
     * Values from ipac_app.properties file
     */
    @Value("${ipac.version}")
    private String ipacVersion;
    
    @Value("${ipac.maxListItems}")
    private Integer maxListItems;
    
    @Value("${ipac.teamedInterfaceId}")
    private Integer teamedInterfaceId;
    
    /**
    * Return the current running version
    *
    * @return The current version of this application as a String
    */    
    final protected String getCurrentIPACVersion(){
        //Return value from properties file or return NONE
    	return ((ipacVersion == null) ? "NONE" : ipacVersion);
    }
    
    /**
    * Return the maximum items to display in lists
    *
    * @return the maximum items to display in lists
    */    
    final protected Integer getMaxListItems(){
        //Return value from properties file
        return maxListItems;
    } 
    
    /**
    * Return the database ID of the Interface defined as the Teamed Interface
    *
    * @return an Integer to represent the Teamed Interface
    */    
    final protected Integer getTeamedInterfaceId(){
    	//Return value from properties file
    	return teamedInterfaceId;
    }    

}
