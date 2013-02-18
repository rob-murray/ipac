
package com.ipac.app.service;

import java.util.List;

import com.ipac.app.model.InterfaceIp;



/**
 * Service for processing InterfaceIps
 * 
 * @author RMurray
 */
public interface InterfaceIpService {
	
	public static enum Error {INVALID_IP, IP_NOT_IN_SUBNET};
    
	/**
	 * Retrieve a InterfaceIp object by its ID
	 * 
	 * @param interfaceIpId The ID of the InterfaceIp to return
	 * @return The InterfaceIp object
	 */
    public InterfaceIp getInterfaceIp( Integer interfaceIpId );
    
    /**
     * Retrieve a InterfaceIp object by the Interface Id it is attached to
     * 
     * @param interfaceId The ID of the Interface the InterfaceIp is attached to
     * @return The InterfaceIp object
     */
    public InterfaceIp getIntIpForId( Integer interfaceId );
    
    /**
     * Persists a new InterfaceIp by attaching it to an Interface by ID
     * 
     * @param interfaceIpObj The prepared InterfaceIp
     * @param interfaceId The ID of the Interface to attach it to
     * @throws IllegalArgumentException
     */
    public void add(InterfaceIp interfaceIpObj, Integer interfaceId) throws IllegalArgumentException;
    
    /**
    * Delete interfaceIp by ID
    * 
    * @param id the id of the existing InterfaceIp
    */
    public void deleteInterfaceIp(Integer id);
    
    
    /**
    * Retrieves interfaceIp for a given IP address
    *
    * @param ipAddress The IP Address as String
    * @return The InterfaceIp object using this IP Address, null if is empty
    */    
    public InterfaceIp getInterfaceIpForIpAddr( String ipAddress );
    
    /**
    * Retrieves LIST of InterfaceIp objects for a subnet ID
    *
    * @param subnetIpAddr The ID of the subnet
    * @return All the InterfaceIp objects on that subnet
    */
    public List<InterfaceIp> getInterfaceIpListBySubnet( Integer subnetIpAddr );
    
    /**
     * Retrieves LIST of next available IP addresses for subnet
     * 
     * @param subnet The Subnet as string
     * @param limit The maximum number of available IP addresses to return
     * @return List of IP Addresses as Strings
     */
    public List<String> getNextAvailableIpForSubnet(String subnet, Integer limit);
    
}
