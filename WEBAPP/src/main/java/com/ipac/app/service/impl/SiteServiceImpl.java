package com.ipac.app.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipac.app.dao.SiteDao;
import com.ipac.app.model.Site;
import com.ipac.app.service.SiteService;

@Service("siteService")
@Transactional
public class SiteServiceImpl implements SiteService{
	
    protected static Logger logger = Logger.getLogger("service");
    
    @Autowired
    private SiteDao siteDao;
    
    
    @Transactional(readOnly = true)
    public List<Site> getAll() {
        
        return siteDao.getAll();
        
    } 
    
    @Transactional(readOnly = true)
    public Site getSite( Integer id ) {
        
        return siteDao.getSite(id);
        
    }	

}
