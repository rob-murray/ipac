
package com.ipac.app.service;

import com.ipac.app.dto.SwitchportDto;
import com.ipac.app.model.Switchport;


/**
 * Service for processing Switchports
 * @author rmurray
 */
public interface SwitchportService {
    
    
    /**
    * Retrieves ONE switchport by id
    *
    * @param id The ID of the Switchport
    * @return a Switchport
    */    
    public Switchport getSwitchport( Integer id );
    
    /**
    * Retrieves ONE switchport by interface id
    *
    * @param interfaceId The ID of the Interface to return the switchport connected to
    * @return a Switchport
    */
    public Switchport getSwitchportByInterfaceId( Integer interfaceId );
      
    
    /**
    * Retrieves ONE switchportDto by interface id
    * 
    * @param interfaceId The ID of the Interface to return the switchport connected to
    * @return a SwitchportDto
    */    
    public SwitchportDto getSwitchportDtoByInterfaceId( Integer interfaceId );
    
    /**
     * Adds a switchport attached to Interfaceid
     * 
     * @param switchportObj The Switchport object to store
     * @param interfaceId The ID of the interface to connect it to
     */
    public void add(Switchport switchportObj, Integer interfaceId);
}
