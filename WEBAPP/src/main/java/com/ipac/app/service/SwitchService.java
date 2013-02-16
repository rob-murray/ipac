
package com.ipac.app.service;

import java.util.List;
 
import com.ipac.app.model.Switch;


/**
 * Service for processing Switches
 * @author rmurray
 */
public interface SwitchService {
    
    /**
    * Retrieves ALL switches
    *
    * @return a list of Switches
    */
    public List<Switch> getAllSwitches();
    
    /**
    * Retrieves ONE switch
    * 
    * @param id The ID of the switch
    * @return a Switch
    */   
    public Switch getSwitch( Integer id );
    
}
