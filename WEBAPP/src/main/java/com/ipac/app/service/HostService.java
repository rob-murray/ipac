
package com.ipac.app.service;

import java.util.List;

import com.ipac.app.dto.HostDto;
import com.ipac.app.model.Host;
import com.ipac.app.model.HostInterfaceIp;


/**
 * Service for processing Hosts
 * 
 * @author RMurray
 */

public interface HostService {     

	/**
	 * Retrieves count of all hosts
	 * 
	 * @return Integer The number of hosts total
	 */
    public Integer getAllHostCount();
    
    /**
     * Retrieves count of all hosts by site
     * 
     * @param siteId The ID of the site to count hosts
     * @return Integer The number of hosts total for the specified site
     */
    public Integer getAllHostCount(Integer siteId);
    
    /**
     * Retrieves count of all hosts by searching name attr for string passed
     * 
     * @param hostSrchStr The string to search for
     * @return Integer The number of hosts total with name containing the string
     */
    public Integer getAllHostCountByName(final String hostSrchStr);
    
    /**
     * List all hosts by pagination criteria
     * 
     * TODO: allow orderby
     * 
     * @param page The page which to display. Number of pages should be total count / pagesize 
     * @param pageSize The maximum number of results per page
     * @return All hosts objects for the pagination
     */
    public List<Host> getAllHosts(int page, int pageSize);
    
    /**
     * List all hosts by pagination criteria for a site
     * 
     * @param siteId The site ID to return results for
     * @param page The page which to display. Number of pages should be total count / pagesize 
     * @param pageSize The maximum number of results per page
     * @return All hosts objects for the pagination
     */
    public List<Host> getAllHosts(Integer siteId, int page, int pageSize);
    
    /**
     * Retreive one host object by ID
     * 
     * @param id The ID of the host object
     * @return The host object
     */
    public Host getHost( Integer id );
    
    /**
     * Return list of hosts with name attr containing string with pagination options
     * 
     * @param hostSrchStr The string to search for
     * @param page The page which to display. Number of pages should be total count / pagesize 
     * @param pageSize The maximum number of results per page
     * @return List of hosts with name containing string
     */
    public List<Host> getHostByName(final String hostSrchStr, int page, int pageSize);
    
    /**
     * Persist one host object
     * 
     * @param host The host object to store
     */
    public void addHost(Host host);
    
    /**
     * Edit one host object
     * 
     * @param host The host object to update
     */
    public void editHost(Host host);
    
    /**
     * Remove one host object
     * 
     * @param id The host object to delete
     */
    public void deleteHost(Integer id);
    
    /**
     * Return a list of HostInterfaceIp objects for a subnet
     * 
     * 
     * @param subnetId The ID of the subnet to list by
     * @return List of HostInterfaceIp objects
     */
    public List<HostInterfaceIp> getHostInterfaceIpBySubnetId( Integer subnetId);
    
    /**
     * Return a hostDto object for the ID of a host passed
     * 
     * @param hostId The host ID
     * @return The HostDto object mapping
     */
    public HostDto getHostDto( Integer hostId );
    
        
}