
package com.ipac.app.dao;

import java.util.List;

import com.ipac.app.model.Switch;


/**
 * Data Access Object for transactional database interactions
 * 
 * @author RMurray
 */
public interface SwitchDao {
    
	/**
	  * Retrieves ALL switches
	  *
	  * @return List<Switch> of switch objects
	  */	
    public List<Switch> getAll();
    
    /**
     * Retrieves ONE switch by ID
     * 
     * @param id The ID of the switch to retrieve
     * @return Switch object
     */
    public Switch getSwitch( Integer id );
    
}
