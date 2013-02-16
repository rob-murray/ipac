
package com.ipac.app.service;

import java.util.List;
 
import com.ipac.app.model.Site;


/**
 * Service for processing Sites
 * @author rmurray
 */
public interface SiteService {
    
    /**
    * Retrieves ALL sites
    *
    * @return a list of Sites
    */
    public List<Site> getAll();
    
    /**
    * Retrieves ONE Site by ID
    *
    * @param the ID of the site
    * @return a Site
    */    
    public Site getSite( Integer id );
    
    
    
}
