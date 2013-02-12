
package com.ipac.app.dao;

import java.util.List;

import com.ipac.app.model.Site;


/**
 * Data Access Object for transactional database interactions
 * 
 * @author RMurray
 */
public interface SiteDao {
    
	/**
	  * Retrieves all Sites
	  *
	  * @return List<Site>
	  */	
    public List<Site> getAll();
    
    /**
     * Retrieves one site by ID
     * 
     * @param id The ID of the site to return
     * @return Site object
     */
    public Site getSite( Integer id );
    
}
