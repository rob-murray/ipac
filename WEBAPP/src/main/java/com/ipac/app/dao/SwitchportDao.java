
package com.ipac.app.dao;

import com.ipac.app.model.Switchport;

/**
 * Data Access Object for transactional database interactions
 * @author RMurray
 */
public interface SwitchportDao {
    
	
	/**
	  * Retrieves ONE switchport by ID
	  *
	  * @param Integer id 
	  * @return Switchport
	  */	
    public Switchport getSwitchport( Integer id );
    
    /**
     * Retrieves ONE switchport by interface id
     *
     * @param Integer interfaceId
     * @return a Switchport
     */
    public Switchport getSwitchportByInterfaceId( Integer interfaceId );
    
    /**
     * Adds a switchport attached to Interface ID
     * 
     * @param HibernateSwitchport switchportObj
     * @param Integer interfaceId
     * @return -
     */ 
    public void add(Switchport switchportObj, Integer interfaceId);
    
}
