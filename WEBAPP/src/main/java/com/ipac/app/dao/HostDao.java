
package com.ipac.app.dao;

import java.util.List;

import com.ipac.app.model.Host;
import com.ipac.app.model.HostInterfaceIp;


/**
 * Data Access Object for transactional database interactions
 * 
 * @author RMurray
 */
public interface HostDao {
    
    /**
    * Retrieves count of all hosts
    * 
    * @return Integer The number of hosts total
    */ 
    public Integer countHosts();
    
    /**
    * Retrieves count of all hosts by site
    * 
    * @param siteId The ID of the site to count hosts
    * @return Integer The number of hosts total for the specified site
    */ 
    public Integer countHosts(Integer siteId);
    
    /**
    * Retrieves count of all hosts by searching name attr for string passed
    * 
    * @param hostSrchStr The string to search for
    * @return Integer The number of hosts total with name containing the string
    */ 
    public Integer countHostsByName(String hostSrchStr);
    
    /**
     * List all hosts by pagination criteria
     * 
     * TODO: allow orderby
     * 
     * @param page The page which to display. Number of pages should be total count / pagesize 
     * @param pageSize The maximum number of results per page
     * @return All hosts objects for the pagination
     */
    public List<Host> getAll(int page, int pageSize);
    
    /**
     * List all hosts by pagination criteria for a site
     * 
     * @param siteId The site ID to return results for
     * @param page The page which to display. Number of pages should be total count / pagesize 
     * @param pageSize The maximum number of results per page
     * @return All hosts objects for the pagination
     */
    public List<Host> getAll(final Integer siteId, int page, int pageSize);
    
    /**
     * Return list of hosts with name attr containing string
     * 
     * @param hostSrchStr The string to search for
     * @return List of hosts with name containing string
     */
    public List<Host> getAllHostsByName(String hostSrchStr);
    
    /**
     * Return list of hosts with name attr containing string with pagination options
     * 
     * @param hostSrchStr The string to search for
     * @param page The page which to display. Number of pages should be total count / pagesize 
     * @param pageSize The maximum number of results per page
     * @return List of hosts with name containing string
     */
    public List<Host> getAllHostsByName(String hostSrchStr, int page, int pageSize);
    
    /**
     * Retreive one host object by ID
     * 
     * @param id The ID of the host object
     * @return The host object
     */
    public Host getHost(final Integer id );
    
    /**
     * Persist one host object
     * 
     * @param host The host object to store
     */
    public void add(Host host);
    
    /**
     * Edit one host object
     * 
     * @param host The host object to update
     */
    public void edit(Host host);
    
    /**
     * Remove one host object
     * 
     * @param id The host object to delete
     */
    public void delete(Integer id);
    
    /**
     * Return a list of HostInterfaceIp objects for a subnet
     * 
     * TODO: should this be here?
     * 
     * @param subnetId The ID of the subnet to list by
     * @return List of HostInterfaceIp objects
     */
    public List<HostInterfaceIp> getHostInterfaceIpBySubnetId( Integer subnetId);
    
    
}
