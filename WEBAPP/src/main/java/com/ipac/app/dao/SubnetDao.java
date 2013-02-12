
package com.ipac.app.dao;

import java.util.List;

import com.ipac.app.model.Subnet;


/**
 * Data Access Object for transactional database interactions
 * @author RMurray
 */
public interface SubnetDao {
    
	/**
	  * Retrieves ALL subnets
	  *
	  * @return List<Subnet>
	  */	
    public List<Subnet> getAll();
    
    /**
     * Retrieves ALL subnets for VLAN Id
     * @param vlanId
     * @return a Subnet
     */
    public List<Subnet> getAll( Integer vlanId );
    
    /**
     * Retrieves ONE subnet by id
     * 
     * @param Integer id
     * @return Subnet
     */
    public Subnet getSubnet( Integer id );
    
    /**
     * Add ONE subnet
     *
     * @return -
     */
    public void add(Subnet subnet);
    
}
