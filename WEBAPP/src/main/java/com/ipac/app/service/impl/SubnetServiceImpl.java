package com.ipac.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipac.app.dao.SubnetDao;
import com.ipac.app.model.Subnet;
import com.ipac.app.model.Vlan;
import com.ipac.app.service.SubnetService;
import com.ipac.app.service.VlanService;

@Service("subnetService")
@Transactional
public class SubnetServiceImpl implements SubnetService {
	
    protected static Logger logger = Logger.getLogger("service");
    
    @Autowired
    private SubnetDao subnetDao;
    
    @Resource(name="vlanService")
    private VlanService vlanService;
    
   
    @Transactional(readOnly = true)
    public Subnet getSubnetById( Integer id ){
        
        return subnetDao.getSubnet(id);
        
    }
    
 
    @Transactional(readOnly = true)
    public List<Subnet> getSubnetsForVlanId( Integer vlanId ) {
        
        return subnetDao.getAll(vlanId);
        
    }    
    
    

    @Transactional(readOnly = true)
    public List<Subnet> getAll() {
        
        return subnetDao.getAll();
        
    }    
    

    public void add(Subnet subnet, Integer vlanId) {
        
        logger.debug("Adding new subnet");
        
        //get vlan
        Vlan vlan = vlanService.getVlanById(vlanId);
        subnet.setVlan(vlan);
        
        subnetDao.add(subnet);
        
    }
    
    @Transactional(readOnly = true)
    public List<String> getNextAvailableIpForSubnet(Integer subnetId, Integer limit){
        
    	Subnet subnet = getSubnetById(subnetId);
    	
        return subnetDao.getNextAvailableIpList(subnet.getIpAddress(), limit);
        
    }    
}
