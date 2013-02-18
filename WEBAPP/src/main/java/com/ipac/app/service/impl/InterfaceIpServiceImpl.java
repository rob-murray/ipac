package com.ipac.app.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipac.app.dao.InterfaceIpDao;
import com.ipac.app.model.InterfaceIp;
import com.ipac.app.model.Subnet;
import com.ipac.app.model.validation.IpValidator;
import com.ipac.app.service.InterfaceIpService;
import com.ipac.app.service.SubnetService;

@Service("interfaceIpService")
@Transactional
public class InterfaceIpServiceImpl implements InterfaceIpService{
	
	protected static Logger logger = Logger.getLogger("service");
	  
    @Autowired
    private InterfaceIpDao interfaceIpDao;
    
    @Autowired 
    private SubnetService subnetService;
        
    @Transactional(readOnly = true)
    public InterfaceIp getInterfaceIp( Integer interfaceIpId ) {    
    
        return interfaceIpDao.getInterfaceIp(interfaceIpId);

    }    
      
    @Transactional(readOnly = true)
    public InterfaceIp getIntIpForId( Integer interfaceId ) {    
    
        return interfaceIpDao.getInterfaceIpByIntId(interfaceId);    

    }
    
    public void add(InterfaceIp interfaceIpObj, Integer interfaceId) {
        logger.debug("Adding new ip address to interface id: "+interfaceId);
        
        Subnet subnet = subnetService.getSubnetById( interfaceIpObj.getSubnetId() );
        
        //
        // Test if the IP address is valid and in the subnet
        //
        if( !IpValidator.isIpInSubnet(interfaceIpObj.getIpAddress(), subnet.getIpAddress() ) ){
        	
        	logger.debug("Failed to match IP address "+ interfaceIpObj.getIpAddress() +" to subnet "+subnet.getIpAddress());
        	
        	throw new IllegalArgumentException( InterfaceIpService.Error.IP_NOT_IN_SUBNET.toString() );
        	
        }
        
        //
        // Ensure the IP address does not exist already
        //
        if( getInterfaceIpForIpAddr(interfaceIpObj.getIpAddress()) != null ){
        	
        	logger.debug("IP address "+ interfaceIpObj.getIpAddress() + " already exists in database.");
        	
        	throw new IllegalArgumentException( InterfaceIpService.Error.INVALID_IP.toString() );
        	
        }
        
        //TODO: check interface is not teamed
        
        
        //add interface ID to obj
        interfaceIpObj.setInterfaceId(interfaceId);
   
        interfaceIpDao.add(interfaceIpObj);
        
    }  
    
    public void deleteInterfaceIp(Integer id) {
        
        interfaceIpDao.delete(id);
        
    }
    
      
    @Transactional(readOnly = true)
    public InterfaceIp getInterfaceIpForIpAddr( String ipAddress ) {
        
        return interfaceIpDao.getInterfaceIpForIpAddr(ipAddress);
        
    }
    
    @Transactional(readOnly = true)
    public List<InterfaceIp> getInterfaceIpListBySubnet( Integer subnetIpAddr ) {
        
        return interfaceIpDao.getAll(subnetIpAddr);
        
    }
    
    @Transactional(readOnly = true)
    public List<String> getNextAvailableIpForSubnet(String subnet, Integer limit){
        
        return interfaceIpDao.getNextAvailableIpList(subnet, limit);
        
    }

}
