
package com.ipac.app.service;

import java.util.List;

import com.ipac.app.model.Subnet;


/**
 * Service for processing Subnets
 *
 */
public interface SubnetService {
    
    /**
    * Retrieves ONE subnets for one subnet id
    * 
    * @param id The ID of the Subnet to return
    * @return a Subnet
    */    
    public Subnet getSubnetById( Integer id );
    
    /**
    * Retrieves ALL subnets for a VLAN Id
    * 
    * @param vlanId The ID of the VLAN to get subnets related to
    * @return List of Subnets attached to VLAN
    */    
    public List<Subnet> getSubnetsForVlanId( Integer vlanId );
    
    
    /**
    * Retrieves ALL subnets
    *
    * @return a list of Subnets
    */
    public List<Subnet> getAll();
    
    /**
     * Store a new Subnet related to a VLAN
     * 
     * @param subnet The Subnet object
     * @param vlanId The ID of the VLAN to attach it to
     */
    public void add(Subnet subnet, Integer vlanId);
    
}
