
package com.ipac.app.service;

import java.util.List;
 
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipac.app.dao.SwitchDao;
import com.ipac.app.model.Switch;


/**
 * Service for processing Switches
 * @author rmurray
 */
@Service("switchService")
@Transactional
public class SwitchService {
    
    protected static Logger logger = Logger.getLogger("service"); 
    
    @Autowired
    private SwitchDao switchDao;  
    
    /**
    * Retrieves ALL switches
    *
    * @return a list of Switches
    */
    @Transactional(readOnly = true)
    public List<Switch> getAllSwitches() {
        
        return switchDao.getAll();
        
    }
    
    /**
    * Retrieves ONE switch
    *
    * @return a Switch
    */   
    @Transactional(readOnly = true)
    public Switch getSwitch( Integer id ) {
        
        return switchDao.getSwitch(id);
        
    }    
    
}
