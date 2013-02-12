
package com.ipac.app.dao;

import java.util.List;

import com.ipac.app.model.Vlan;


/**
 * Data Access Object for transactional database interactions
 * @author RMurray
 */
public interface VlanDao {
    
    public List<Vlan> getAll();
    public List<Vlan> getAll(Integer siteId);
    public Vlan getVlan( Integer id );
    public Vlan getVlanByInterfaceId( Integer interfaceId );
    public void add(Vlan vlan);
    
}
