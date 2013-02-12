
package com.ipac.app.dao;

import java.util.List;

import com.ipac.app.model.Vlan;


/**
 * Data Access Object for transactional database interactions
 * 
 * @author RMurray
 */
public interface VlanDao {
    
	/**
	 * Return all VLANs
	 * 
	 * @return the List<Vlan> of VLANs to return
	 */
    public List<Vlan> getAll();
    
    /**
     * Return a list of VLANs related to a site
     * 
     * @param siteId The ID of the site to get VLANs for
     * @return the List<Vlan> of VLANs to return
     */
    public List<Vlan> getAll(Integer siteId);
    
    /**
     * Retrieve a VLAN by ID
     * 
     * @param id The ID of the VLAN to return. This is not the Cisco VLAN ID but the database ID
     * @return The VLAN object to return
     */
    public Vlan getVlan( Integer id );
    
    /**
     * Return a VLAN on which an Interface is situated
     * 
     * @param interfaceId The ID of the interface to get the VLAN for
     * @return The VLAN object to return
     */
    public Vlan getVlanByInterfaceId( Integer interfaceId );
    
    /**
     * Persist a VLAN object to the database
     * 
     * @param vlan The VLAN object to store
     */
    public void add(Vlan vlan);
    
}
