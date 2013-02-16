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
    
    /**
    * Retrieves ONE subnets for one subnet id
    * @param id
    * @return a Subnet
    */    
    @Transactional(readOnly = true)
    public Subnet getSubnetById( Integer id ){
        
        return subnetDao.getSubnet(id);
        
    }
    
    /**
    * Retrieves ALL subnets for VLAN Id
    * @param vlanId
    * @return a Subnet
    */    
    @Transactional(readOnly = true)
    public List<Subnet> getSubnetsForVlanId( Integer vlanId ) {
        
        return subnetDao.getAll(vlanId);
        
    }    
    
    
    /**
    * Retrieves ALL subnets
    *
    * @return a list of Subnets
    */
    @Transactional(readOnly = true)
    public List<Subnet> getAll() {
        
        return subnetDao.getAll();
        
    }    
    
    /**
    * Add ONE subnet
    *
    * @return -
    */
    public void add(Subnet subnet, Integer vlanId) {
        
        logger.debug("Adding new subnet");
        
        //get vlan
        Vlan vlan = vlanService.getVlanById(vlanId);
        subnet.setVlan(vlan);
        
        subnetDao.add(subnet);
        
    }
}
