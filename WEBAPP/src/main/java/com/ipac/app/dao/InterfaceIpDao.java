
package com.ipac.app.dao;

import java.util.List;

import com.ipac.app.model.InterfaceIp;


/**
 * Data Access Object for transactional database interactions
 * 
 * @author RMurray
 */
public interface InterfaceIpDao {
    
	/**
	    * Retrieves an interfaceIp for a given interfaceIp ID
	    * 
	    * @param interfaceIpId The ID of the interfaceIp
	    * @return interfaceIp object
	    */
    public InterfaceIp getInterfaceIp( Integer interfaceIpId );
    
    /**
     * Retrieves an interfaceIp for a given Interface ID
     * 
     * @param interfaceId The ID of the Interface
     * @return interfaceIp object
     */
    public InterfaceIp getInterfaceIpByIntId( Integer interfaceId );
    
    /**
     * Retrieves an interfaceIp for a given IP address
     * 
     * @param ipAddress The IP address of the interface
     * @return interfaceIp object
     */
    public InterfaceIp getInterfaceIpForIpAddr( String ipAddress );
    
    /**
     * Retrieves LIST of next available IP addresses as Strings for subnet
     * 
     * @param subnet The subnet from which to return available IP addresses
     * @param limit The maximum number of IP addresses to return
     * @return List<String> of IP addresses
     */
    public List<String> getNextAvailableIpList(String subnet, Integer limit);
    
    /**
     * Retrieves LIST of interfaceIp objects for a given subnet
     * 
     * @param subnetIpAddr surely this is the ID TODO: check
     * @return List<InterfaceIp> of IP address objects
     */
    public List<InterfaceIp> getAll( Integer subnetIpAddr );
    
    /**
     * Persists an IP address attached to Interface
     * 
     * @param interfaceObj The Interface IP address object
     */
    public void add(InterfaceIp interfaceIpObj);
    
    /**
     * Deletes an IP address by ID
     * 
     * @param interfaceIpId The ID of the Interface IP to delete
     */
    public void delete(Integer interfaceIpId);
    
}
