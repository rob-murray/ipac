
package com.ipac.app.service;

import java.util.List;

import com.ipac.app.dto.InterfaceDto;
import com.ipac.app.dto.TeamedInterfaceDto;
import com.ipac.app.model.Interface;




/**
 * Service for processing Interfaces
 * @author rmurray
 */

public interface InterfaceService {
    

    /**
    * Retrieves all Interfaces attached to HOST as InterfaceDto List
    * 
    * @param hostId The ID of the host
    * @return a list of InterfaceDto objects for the host
    */    
    public List<InterfaceDto> getInterfaceDtosByHost( Integer hostId );
           
    
    /**
    * Retrieves Interface by ID
    * 
    * @param interfaceId The ID of the Interface
    * @return The Interface object
    */    
    public Interface getInterfaceById( Integer interfaceId );
    
    /**
    * Returns the ID of the host related to the ID of the Interface passed
    * 
    * @param interfaceId The ID of an Interface
    * @return The ID of the host related to this ID, null if nothing
    */    
    public Integer getHostIdFromInterface( Integer interfaceId );
    
    /**
    * Retrieves all Interfaces attached to HOST
    * 
    * @param hostId The ID of the Host
    * @return a list of Interfaces
    */    
    public List<Interface> getInterfacesForHost( Integer hostId );
    
    
    /**
    * Adds an Interface attached to HOST
    * 
    * @param hostId The ID of the Host to attach the new Interface
    * @param interfaceObj The new Interface
    */ 
    public void add(Integer hostId, Interface interfaceObj);
    
    /**
    * Edit ONE interface
    *
    * @return -
    */
    public void edit(Interface interfaceObj);
    
    /**
    * Delete ONE interface
    *
    * @param interfaceObj The Interface to delete
    */
    public void delete(Interface interfaceObj);
    
    
    /**
    * Adds a new Interface and call method to set teaming ref to other interfaces
    * 
    * @param teamedInterfaceDto The Dto for a TeamedInterface
    */
    public void teamInterfaces(TeamedInterfaceDto teamedInterfaceDto);
    
    /**
     * Method to set teaming ref to other interfaces by teamedInterfaceId
     * 
     * @param teamedInterfaceId The ID of the teamedInterface
     * @param newTeamedId TODO: what is this
     */
    public void setUpdatedTeamedInfo(Integer teamedInterfaceId, Integer newTeamedId);
    
    /**
     * Method to set teaming ref to other interfaces passed in List
     * 
     * @param interfaceList The List of Interface IDs to team
     * @param teamedInterfaceId TODO: what is this
     */
    public void setTeamedInfo(List<Integer> interfaceList, Integer teamedInterfaceId);
    
}
