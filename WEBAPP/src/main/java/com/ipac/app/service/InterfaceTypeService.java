
package com.ipac.app.service;

import java.util.List;
 
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipac.app.dao.InterfaceTypeDao;
import com.ipac.app.model.InterfaceType;


/**
 * Service for processing Interfaces
 * @author rmurray
 */
@Service("interfaceTypeService")
@Transactional
public class InterfaceTypeService {
    
    protected static Logger logger = Logger.getLogger("service");
  
    @Autowired
    private InterfaceTypeDao interfaceTypeDao;    
    
    /**
    * Retrieves ALL interfacetypes
    * @params selectable : boolean = true
    * @return a list of interfacetypes
    */
    @Transactional(readOnly = true)
    public List<InterfaceType> getAll(Boolean selectableFilter) {
        
        return interfaceTypeDao.getAll(selectableFilter);
        
    } 
    
    /**
    * Retrieves ONE interfacetype
    *
    * @return interfacetype
    */  
    @Transactional(readOnly = true)
    public InterfaceType getIntTypeForId( Integer typeId ){
        
        return interfaceTypeDao.getInterfaceType(typeId);
        
    }
    
}
