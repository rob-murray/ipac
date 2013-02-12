
package com.ipac.app.dao;

import java.util.List;

import com.ipac.app.model.Subnet;


/**
 * Data Access Object for transactional database interactions
 * 
 * @author RMurray
 */
public interface SubnetDao {
    
	/**
	  * Retrieves ALL subnets
	  *
	  * @return List<Subnet> of subnets
	  */	
    public List<Subnet> getAll();
    
    /**
     * Retrieves ALL subnets for a particular VLAN ID
     * 
     * @param vlanId The ID of the VLAN to retrieve subnets for
     * @return List<Subnet> of subnets
     */
    public List<Subnet> getAll( Integer vlanId );
    
    /**
     * Retrieves ONE subnet by id
     * 
     * @param id The ID of the subnet to retrieve
     * @return Subnet object
     */
    public Subnet getSubnet( Integer id );
    
    /**
     * Persist ONE subnet to the database
     *
     * @param Subnet object to store
     */
    public void add(Subnet subnet);
    
}
