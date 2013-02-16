package com.ipac.app.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipac.app.dao.InterfaceTypeDao;
import com.ipac.app.model.InterfaceType;
import com.ipac.app.service.InterfaceTypeService;

@Service("interfaceTypeService")
@Transactional
public class InterfaceTypeServiceImpl implements InterfaceTypeService {
	
	protected static Logger logger = Logger.getLogger("service");
	  
    @Autowired
    private InterfaceTypeDao interfaceTypeDao;    
    
    @Transactional(readOnly = true)
    public List<InterfaceType> getAll(Boolean onlySelectableInterfaceTypes) {
        
        return interfaceTypeDao.getAll(onlySelectableInterfaceTypes);
        
    } 
     
    @Transactional(readOnly = true)
    public InterfaceType getIntTypeForId( Integer typeId ){
        
        return interfaceTypeDao.getInterfaceType(typeId);
        
    }	

}
