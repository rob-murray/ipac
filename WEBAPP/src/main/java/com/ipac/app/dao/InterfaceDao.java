
package com.ipac.app.dao;

import java.util.List;

import com.ipac.app.model.Interface;


/**
 * Data Access Object for transactional database interactions
 * @author RMurray
 */
public interface InterfaceDao {
    
	/**
	    * Retrieves Interface by id
	    * 
	    * @param Integer interfaceId
	    * @return Interface
	    */ 	
    public Interface getInterface( Integer interfaceId );
    
    /**
     * Retrieves all Interfaces attached to HOST as Interfaces
     * 
     * @param Integer hostId
     * @return List Interface
     */
    public List<Interface> getAll( Integer hostId );
    
    /**
     * Retrieves all Host id reference by interface id
     * 
     * @param Integer interfaceId
     * @return Integer hostId
     */
    public Integer getHostIdFromInterface( Integer interfaceId );
    
    /**
     * Get COUNT(*) query for host
     * 
     * @param Integer hostId
     * @return Integer COUNT
     */
    public int getInterfaceCount( Integer hostId );
    
    /**
     * Adds an Interface attached to HOST @param
     * 
     * @param hostId int, Interface interfaceObj
     * @return -
     */
    public void add(Interface interfaceObj);
    
    /**
     * Edit ONE interface
     *
     * @return -
     */
    public void edit(Interface interfaceObj);
    
    /**
     * Delete ONE interface
     * @param id the id of the existing interface
     * @return -
     */
    public void delete(Integer id);
    
    /**
     * Sets teamed interface data by teamed inteface id
     * 
     * @param Integer teamedInterfaceId, Integer newTeamedId
     * @return -
     */
    public void updateTeamedInterfaces(Integer teamedInterfaceId, Integer newTeamedId);
    
    /**
     * Adds a new teamed interface by SQL statement
     * 
     * @param String constructedSql
     * @return -
     */
    public void setTeamedInterfaces(String interfacesSqlStr, Integer teamedInterfaceId);
    
    
}
