
package com.ipac.app.service;

import javax.annotation.Resource;
 
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipac.app.dao.SwitchportDao;
import com.ipac.app.dto.SwitchportDto;
import com.ipac.app.model.Switchport;


/**
 * Service for processing Switchports
 * @author rmurray
 */
@Service("switchportService")
@Transactional
public class SwitchportService {
    
    protected static Logger logger = Logger.getLogger("service");
    
    @Autowired
    private SwitchportDao switchportDao;    
    
    @Resource(name="switchService")
    private SwitchService switchService;    
    
    
    /**
    * Retrieves ONE switchport by id
    *
    * @return a Switchport
    */    
    @Transactional(readOnly = true)
    public Switchport getSwitchport( Integer id ) {
        
        return switchportDao.getSwitchport(id);
        
    }
    
    /**
    * Retrieves ONE switchport by interface id
    *
    * @return a Switchport
    */    
    @Transactional(readOnly = true)
    public Switchport getSwitchportByInterfaceId( Integer interfaceId ) {
        
        return switchportDao.getSwitchportByInterfaceId(interfaceId);

    }    
      
    
    /**
    * Retrieves ONE switchportDto by interface id
    *
    * @return a SwitchportDto
    */    
    @Transactional(readOnly = true)
    public SwitchportDto getSwitchportDtoByInterfaceId( Integer interfaceId ) {

        logger.debug("Retrieving switchport dto for interface id: "+interfaceId);
        
        //Get switchport object
        Switchport sw = getSwitchportByInterfaceId( interfaceId );
        
        //create new dto
        SwitchportDto swDto = new SwitchportDto();
        
        //If there IS a switchport attached to this interface then assign data to dto
        if(sw != null){
            
            swDto.setId( sw.getId() );
            swDto.setChassis( sw.getChassis() );
            swDto.setBlade( sw.getBlade() );
            swDto.setPort( sw.getPort() );

            swDto.setSwitchObj( switchService.getSwitch( sw.getSwitchId() ) );            
            
        }else{
            //else return null
            swDto = null;
        }
        
        return swDto;
        
    }
    
    /**
    * Adds a switchport attached to Interfaceid @param
    * 
    * @param HibernateSwitchport switchportObj, Integer interfaceId
    * @return -
    */ 
    public void add(Switchport switchportObj, Integer interfaceId) {
        
        //TODO: check interface is physical
        
        switchportDao.add(switchportObj, interfaceId);
    }     
    

}
