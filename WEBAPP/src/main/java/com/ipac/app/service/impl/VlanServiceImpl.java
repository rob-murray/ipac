package com.ipac.app.service.impl;

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
import com.ipac.app.service.SubnetService;
import com.ipac.app.service.VlanService;

@Service("vlanService")
@Transactional
public class VlanServiceImpl implements VlanService{
	
    protected static Logger logger = Logger.getLogger("service");
    
    @Autowired
    private VlanDao vlanDao;   
    
    @Resource(name="subnetService")
    private SubnetService subnetService;    
    
    
    @Transactional(readOnly = true)
    public List<Vlan> getAll() {
        
        return vlanDao.getAll();
        
    }
    

    @Transactional(readOnly = true)
    public List<Vlan> getAll(Integer siteId) {
        
        return vlanDao.getAll(siteId);
        
    }    
 
    
    public void add(Vlan vlan) {
        
        vlanDao.add(vlan);
        
    }     
    

    @Transactional(readOnly = true)
    public Vlan getVlanById( Integer id ){
        
        return vlanDao.getVlan(id);
        
    }
    

    @Transactional(readOnly = true)
    public Vlan getVlanByInterfaceId( Integer interfaceId ){
        
        return vlanDao.getVlanByInterfaceId(interfaceId);
        
    }    
    

    @Transactional(readOnly = true)
    public VlanDto getVlanDto( Integer vlanId ){
        
        //Get vlan obj
    	Vlan vlan = getVlanById(vlanId);
        
        return prepareDto( vlan );
    }    
    

    @Transactional(readOnly = true)
    public List<VlanDto> getVlanDtoList(){
        
        List<Vlan> vlans = getAll();
        
    	return prepareDtoList(vlans);
        
    }
    
  
    @Transactional(readOnly = true)
    public List<VlanDto> getVlanDtoList( Integer siteId ){
        
        List<Vlan> vlans = getAll(siteId);
        
        return prepareDtoList(vlans);
        
    }    
    
    /**
     * Return a VlanDto for the Vlan passed
     * 
     * @param vlan The VLAN object to convert to a dto
     * @return VlanDto representation of the vlan
     */
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
    * 
    * @param List<Vlan> vlans to get Dtos for
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
