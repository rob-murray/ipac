package com.ipac.app.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.ipac.app.service.UserService;


/**
 * IpacWebController default webcontroller for application
 * 
 * @author rmurray
 *
 */
public class IpacWebController {
	
	protected static Logger logger = Logger.getLogger("controller");
	
    @Resource(name="userService")
	protected UserService userService;

    /**
     * Values from ipac properties file
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
    * @return String
    */    
    protected String getCurrentIPACVersion(){
        //Return value from properties file or return NONE
    	return ((ipacVersion == null) ? "NONE" : ipacVersion);
    }
    
    /**
    * Return the maximum items to display in lists
    *
    * @return Integer
    */    
    protected Integer getMaxListItems(){
        //Return value from properties file
        return maxListItems;
    } 
    
    /**
    * Return the 
    *
    * @return Integer
    */    
    protected Integer getTeamedInterfaceId(){
    	//Return value from properties file
    	return teamedInterfaceId;
    }    

}
