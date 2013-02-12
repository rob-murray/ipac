
package com.ipac.app.service;

import java.util.List;
 
import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ipac.app.dao.InterfaceIpDao;
import com.ipac.app.model.InterfaceIp;



/**
 * Service for processing InterfaceIps
 * @author RMurray
 */
@Service("interfaceIpService")
@Transactional
public class InterfaceIpService {
    
    
    protected static Logger logger = Logger.getLogger("service");
  
    @Autowired
    private InterfaceIpDao interfaceIpDao;
    
    /**
    * Retrieves ONE interfaceIp for a given interface ID
    *
    * @return a interfaceIp
    */    
    @Transactional(readOnly = true)
    public InterfaceIp getInterfaceIp( Integer interfaceIpId ) {    
    
        return interfaceIpDao.getInterfaceIp(interfaceIpId);

    }    
    
    /**
    * Retrieves ONE interfaceIp for a given interface ID
    *
    * @return a interfaceIp
    */    
    @Transactional(readOnly = true)
    public InterfaceIp getIntIpForId( Integer interfaceId ) {    
    
        return interfaceIpDao.getInterfaceIpByIntId(interfaceId);    

    }
    
    /**
    * Adds an IP address attached to Interfaceid
    * 
    * @param interfaceId int, Interface interfaceObj
    * @return -
    */ 
    public void add(InterfaceIp interfaceIpObj, Integer interfaceId) {
        logger.debug("Adding new ip address to interface id: "+interfaceId);
        
        //TODO: check interface is not teamed
        
        //add interface ID to obj
        interfaceIpObj.setInterfaceId(interfaceId);
   
        interfaceIpDao.add(interfaceIpObj);
        
    }  
    
    /**
    * Delete ONE interfaceIp
    * @param id the id of the existing interfaceip
    * @return -
    */
    public void deleteInterfaceIp(Integer id) {
        
        interfaceIpDao.delete(id);
        
    }
    
    
    /**
    * Retrieves ONE interfaceIp for a given IP address
    *
    * @return a interfaceIp
    */    
    @Transactional(readOnly = true)
    public InterfaceIp getInterfaceIpForIpAddr( String ipAddress ) {
        
        return interfaceIpDao.getInterfaceIpForIpAddr(ipAddress);
        
    }
    
    /**
    * Retrieves LIST of interfaceIp for a given IP address of subnet
    *
    * @return List<InterfaceIp> interfaceIp
    */
    @Transactional(readOnly = true)
    public List<InterfaceIp> getInterfaceIpListBySubnet( Integer subnetIpAddr ) {
        
        return interfaceIpDao.getAll(subnetIpAddr);
        
    }
    
    /**
    * Retrieves LIST of next available IP addresses for subnet
    * @params String subnet, Integer limit
    * @return List<String> interfaceIpList
    */
    @Transactional(readOnly = true)
    public List<String> getNextAvailableIpForSubnet(String subnet, Integer limit){
        
        return interfaceIpDao.getNextAvailableIpList(subnet, limit);
        
    }
    
}
