
package com.ipac.app.dao;

import java.util.List;

import com.ipac.app.model.InterfaceType;


/**
 * Data Access Object for transactional database interactions
 * @author RMurray
 */
public interface InterfaceTypeDao {
    
	/**
     * Retrieves all Interface Types
     * 
     * @return List<InterfaceType>
     */
    public List<InterfaceType> getAll();
    
    /**
     * Retrieves all Interfaces Types
     * 
     * @param Boolean Whether to return only types the can be selected
     * @return List<InterfaceType>
     */
    public List<InterfaceType> getAll(Boolean selectableFilter);
    
    /**
     * Retrieves a specified InterfaceType by ID
     * 
     * @param Integer typeId
     * @return InterfaceType
     */
    public InterfaceType getInterfaceType( Integer typeId );
    
    
}
