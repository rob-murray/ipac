
package com.ipac.app.dao;

import java.util.List;

import com.ipac.app.model.Interface;


/**
 * Data Access Object for transactional database interactions
 * 
 * @author RMurray
 */
public interface InterfaceDao {
    
	/**
	    * Retrieves Interface by id
	    * 
	    * @param interfaceId The ID of the Interface to retrieve
	    * @return Interface
	    */ 	
    public Interface getInterface( Integer interfaceId );
    
    /**
     * Retrieves all Interfaces attached to HOST as Interfaces
     * 
     * @param hostId The ID of the host
     * @return List Interface
     */
    public List<Interface> getAll( Integer hostId );
    
    /**
     * Returns the Host ID to which an Interface belongs
     * 
     * @param interfaceId The ID of the Interface to retrieve
     * @return Integer hostId
     */
    public Integer getHostIdFromInterface( Integer interfaceId );
    
    /**
     * Returns a count of the number of interfaces attached to a host
     * 
     * @param hostId The ID of the host
     * @return Integer The count
     */
    public int getInterfaceCount( Integer hostId );
    
    /**
     * Persist an Interface object to database
     * 
     * @param interfaceObj The object to store
     */
    public void add(Interface interfaceObj);
    
    /**
     * Edit and persist an Interface object to database
     *
     * @param interfaceObj The object to store
     */
    public void edit(Interface interfaceObj);
    
    /**
     * Delete ONE interface by ID
     * 
     * @param id the id of the existing interface
     */
    public void delete(Integer id);
    
    /**
     * Sets teamed interface data by teamed inteface id
     * 
     * @param teamedInterfaceId The ID of the teamed interface
     * @param newTeamedId The ID of the new teamed interface
     * 
     */
    public void updateTeamedInterfaces(Integer teamedInterfaceId, Integer newTeamedId);
    
    /**
     * Adds a new teamed interface by SQL statement
     * 
     * @param String constructedSql
     * @param teamedInterfaceId The ID of the teamed interface
     * 
     */
    public void setTeamedInterfaces(String interfacesSqlStr, Integer teamedInterfaceId);
    
    
}
