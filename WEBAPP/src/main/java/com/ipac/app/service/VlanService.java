
package com.ipac.app.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipac.app.dao.VlanDao;
import com.ipac.app.dto.VlanDto;
import com.ipac.app.model.Vlan;



/**
 * Service for processing Vlans
 * @author rmurray
 */
@Service("vlanService")
@Transactional
public class VlanService {
    
    protected static Logger logger = Logger.getLogger("service");
    
    @Autowired
    private VlanDao vlanDao;   
    
    @Resource(name="subnetService")
    private SubnetService subnetService;    
    
    
    /**
    * Retrieves ALL Vlans
    *
    * @return a list of Vlans
    */
    @Transactional(readOnly = true)
    public List<Vlan> getAll() {
        
        return vlanDao.getAll();
        
    }
    
    /**
    * Retrieves ALL Vlans by site id
    * @params int site
    * @return a list of Vlans
    */
    @Transactional(readOnly = true)
    public List<Vlan> getAll(Integer siteId) {
        
        return vlanDao.getAll(siteId);
        
    }    
 
    
    /**
    * Add ONE vlan
    *
    * @return -
    */
    public void add(Vlan vlan) {
        
        vlanDao.add(vlan);
        
    }     
    
    /**
    * Retrieves ONE vlan for one id
    *
    * @return a Vlan
    */ 
    @Transactional(readOnly = true)
    public Vlan getVlanById( Integer id ){
        
        return vlanDao.getVlan(id);
        
    }
    
    /**
    * Retrieves ONE vlan for INTERFACE id
    * @params Integer interfaceId
    * @return a Vlan
    */  
    @Transactional(readOnly = true)
    public Vlan getVlanByInterfaceId( Integer interfaceId ){
        
        return vlanDao.getVlanByInterfaceId(interfaceId);
        
    }    
    
    /**
    * Returns VlanDto obj
    * @params -
    * @return vlanDto
    */  
    @Transactional(readOnly = true)
    public VlanDto getVlanDto( Integer vlanId ){
        
        //Get vlan obj
    	Vlan vlan = getVlanById(vlanId);
        
        return prepareDto( vlan );
    }    
    
    /**
    * Returns list of VlanDto objs
    * @params -
    * @return List of vlanDtos
    */  
    @Transactional(readOnly = true)
    public List<VlanDto> getVlanDtoList(){
        
        List<Vlan> vlans = getAll();
        
    	return prepareDtoList(vlans);
        
    }
    
    /**
    * Returns list of VlanDto objs
    * @params siteId
    * @return List of vlanDtos
    */  
    @Transactional(readOnly = true)
    public List<VlanDto> getVlanDtoList( Integer siteId ){
        
        List<Vlan> vlans = getAll(siteId);
        
        return prepareDtoList(vlans);
        
    }    
    
    @Transactional(readOnly = true)
    private VlanDto prepareDto(Vlan vlan){
        
        // Create new data transfer object
        VlanDto dto = new VlanDto();
            
        dto.setId( vlan.getId() );
        dto.setName( vlan.getName() );
        dto.setDescr( vlan.getDescr() );
        dto.setCreated_by( vlan.getCreatedBy() );
        dto.setUpdated_by( vlan.getUpdatedBy() );
        dto.setSite( vlan.getSite() );
        dto.setRoutable( vlan.getRoutable() );
        dto.setSwVlanId( vlan.getSwVlanId() );
        dto.setSubnetList( subnetService.getSubnetsForVlanId( vlan.getId() ) );    
            
        return dto;
        
    }
    
    /**
    * Returns list of VlanDto objs
    * @params List<Vlan> vlans
    * @return List of vlanDtos
    */   
    @Transactional(readOnly = true)
    private List<VlanDto> prepareDtoList( List<Vlan> vlans ){
        
        // Prepare model object
    	List<VlanDto> vlanDtoList = new ArrayList<VlanDto>();
    	
    	for (Vlan vlan: vlans) {
            
            vlanDtoList.add( prepareDto(vlan) );
    		
    	}        
        
        return vlanDtoList;
        
    }
  
    
    
}
