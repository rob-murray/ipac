
package com.ipac.app.service;

import java.util.List;

import com.ipac.app.dto.VlanDto;
import com.ipac.app.model.Vlan;



/**
 * Service for processing Vlans
 * @author rmurray
 */
public interface VlanService {
    

    /**
    * Retrieves ALL Vlans
    *
    * @return a list of Vlans
    */
    public List<Vlan> getAll();
    
    /**
    * Retrieves ALL Vlans by site id
    * 
    * @param siteId The ID of the Site to get VLANs for
    * @return a list of Vlans
    */
    public List<Vlan> getAll(Integer siteId);
 
    
    /**
    * Add ONE vlan
    * 
    * @param vlan The VLAN object to store
    */
    public void add(Vlan vlan);
    
    /**
    * Retrieves ONE vlan for one id
    *
    * @param id The ID of the Vlan to return
    * @return a Vlan
    */ 
    public Vlan getVlanById( Integer id );
    
    /**
    * Retrieves ONE vlan for INTERFACE id
    * 
    * @param interfaceId The ID of the Interface to get the Vlan it is on
    * @return a Vlan
    */  
    public Vlan getVlanByInterfaceId( Integer interfaceId );
    
    /**
    * Returns VlanDto for the ID of a VLAN
    * 
    * @param vlanId The ID of the VLAN
    * @return a Dto for the Vlan
    */
    public VlanDto getVlanDto( Integer vlanId );
    
    /**
    * Returns list of VlanDto for all VLANs
    * 
    * @return List of vlanDtos
    */  
    public List<VlanDto> getVlanDtoList();
    
    /**
    * Returns list of VlanDto for all VLANs for one site
    * 
    * @param siteId The ID of the site to return the Dtos for
    * @return List of vlanDtos
    */  
    public List<VlanDto> getVlanDtoList( Integer siteId );
  
    
    
}
