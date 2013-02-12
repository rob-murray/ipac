
package com.ipac.app.dao;

import java.util.List;

import com.ipac.app.model.Host;
import com.ipac.app.model.HostInterfaceIp;


/**
 * Data Access Object for transactional database interactions
 * @author RMurray
 */
public interface HostDao {
    
    /**
    * Retrieves count of ALL hosts
    * @params -
    * @return integer
    */ 
    public Integer countHosts();
    
    /**
    * Retrieves count of ALL hosts
    * @params SiteID
    * @return integer
    */ 
    public Integer countHosts(Integer siteId);
    
    /**
    * Retrieves count of ALL hosts by name - search
    * @params String searchname
    * @return integer
    */ 
    public Integer countHostsByName(String hostSrchStr);
    
    /**
    * Retrieves ALL hosts
    * @params int page, int pageSize
    * @return a list of Hosts
    */
    public List<Host> getAll(int page, int pageSize);
    
    /**
    * Retrieves ALL hosts
    * @params Integer siteId, int page, int pageSize
    * @return a list of Hosts
    */
    public List<Host> getAll(final Integer siteId, int page, int pageSize);
    
    /**
    * Retrieves List of hosts searched by name
    * @params String hostSrchStr
    * @return a list of Hosts
    */
    public List<Host> getAllHostsByName(String hostSrchStr);
    
    /**
     * Retrieves List of hosts searched by name
     * @params String hostSrchStr, int page, int pageSize
     * @return a list of Hosts
     */
    public List<Host> getAllHostsByName(String hostSrchStr, int page, int pageSize);
    
    /**
     * Retrieves ONE host
     *
     * @return a Host
     */
    public Host getHost(final Integer id );
    
    /**
     * Add ONE host
     *
     * @return -
     */ 
    public void add(Host host);
    
    /**
     * Edit ONE host
     *
     * @return -
     */
    public void edit(Host host);
    
    /**
     * Delete ONE host
     * @param id the id of the existing host
     * @return -
     */
    public void delete(Integer id);
    
    /**
     * Retrieves LIST of HostInterfaceIp for subnet id
     * 
     * @param subnetId int
     * @return a list of HostInterfaceIp - non-entity obj
     */
    public List<HostInterfaceIp> getHostInterfaceIpBySubnetId( Integer subnetId);
    
    
}
