
package com.ipac.app.dao;

import java.util.List;

import com.ipac.app.model.Switch;


/**
 * Data Access Object for transactional database interactions
 * @author RMurray
 */
public interface SwitchDao {
    
	/**
	  * Retrieves ALL switches
	  *
	  * @return List<Switch>
	  */	
    public List<Switch> getAll();
    
    /**
     * Retrieves ONE switch by ID
     * 
     * @param Integer id
     * @return Switch
     */
    public Switch getSwitch( Integer id );
    
}
