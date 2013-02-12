
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
	  * @param id The ID of the switchport
	  * @return Switchport object
	  */	
    public Switchport getSwitchport( Integer id );
    
    /**
     * Retrieves the switchport attached to an Interface by Interface ID
     *
     * @param interfaceId The ID of the interface to relate to the switchport
     * @return a Switchport object
     */
    public Switchport getSwitchportByInterfaceId( Integer interfaceId );
    
    /**
     * Persist a switchport to database. Requires the ID of the interface to relate to
     * 
     * @param switchportObj The Switchport to store
     * @param interfaceId The ID of the related interface TODO: this dao shouldnt be doing this
     */ 
    public void add(Switchport switchportObj, Integer interfaceId);
    
}
