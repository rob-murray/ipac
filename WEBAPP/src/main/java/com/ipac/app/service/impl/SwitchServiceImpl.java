package com.ipac.app.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipac.app.dao.SwitchDao;
import com.ipac.app.model.Switch;
import com.ipac.app.service.SwitchService;

@Service("switchService")
@Transactional
public class SwitchServiceImpl implements SwitchService {
	
    protected static Logger logger = Logger.getLogger("service"); 
    
    @Autowired
    private SwitchDao switchDao;  
    
    @Transactional(readOnly = true)
    public List<Switch> getAllSwitches() {
        
        return switchDao.getAll();
        
    }
      
    @Transactional(readOnly = true)
    public Switch getSwitch( Integer id ) {
        
        return switchDao.getSwitch(id);
        
    }	

}
