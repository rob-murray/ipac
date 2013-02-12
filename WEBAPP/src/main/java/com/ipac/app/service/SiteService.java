
package com.ipac.app.service;

import java.util.List;
 
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipac.app.dao.SiteDao;
import com.ipac.app.model.Site;


/**
 * Service for processing Sites
 * @author rmurray
 */
@Service("siteService")
@Transactional
public class SiteService {
    
    
    protected static Logger logger = Logger.getLogger("service");
    
    @Autowired
    private SiteDao siteDao;
    
    
    /**
    * Retrieves ALL sites
    *
    * @return a list of Sites
    */
    @Transactional(readOnly = true)
    public List<Site> getAll() {
        
        return siteDao.getAll();
        
    } 
    
    /**
    * Retrieves ONE Site
    *
    * @return a Site
    */    
    @Transactional(readOnly = true)
    public Site getSite( Integer id ) {
        
        return siteDao.getSite(id);
        
    }
    
    
    
}
