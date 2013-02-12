
package com.ipac.app.dao;

import java.util.List;

import com.ipac.app.model.InterfaceIp;


/**
 * Data Access Object for transactional database interactions
 * @author RMurray
 */
public interface InterfaceIpDao {
    
	/**
	    * Retrieves ONE interfaceIp for a given interfaceIp ID
	    * @param Integer interfaceIpId
	    * @return interfaceIp
	    */
    public InterfaceIp getInterfaceIp( Integer interfaceIpId );
    
    /**
     * Retrieves ONE interfaceIp for a given interface ID
     * @param Integer interfaceId
     * @return interfaceIp
     */
    public InterfaceIp getInterfaceIpByIntId( Integer interfaceId );
    
    /**
     * Retrieves ONE interfaceIp for a given IP address
     * @param String IP address
     * @return a interfaceIp
     */
    public InterfaceIp getInterfaceIpForIpAddr( String ipAddress );
    
    /**
     * Retrieves LIST of next available IP addresses as Strings for subnet
     * @param String subnet
     * @param Integer limit
     * @return List<String> interfaceIpList
     */
    public List<String> getNextAvailableIpList(String subnet, Integer limit);
    
    /**
     * Retrieves LIST of interfaceIp for a given IP address of subnet
     * @param Integer Subnet IPAddr
     * @return List<InterfaceIp> interfaceIp
     */
    public List<InterfaceIp> getAll( Integer subnetIpAddr );
    
    /**
     * Adds an IP address attached to Interfaceid
     * 
     * @param interfaceId int, Interface interfaceObj
     * @return -
     */
    public void add(InterfaceIp interfaceIpObj);
    
    /**
     * Deletes an IP address attached
     * 
     * @param Integer interfaceIpId
     * @return -
     */
    public void delete(Integer interfaceIpId);
    
}
