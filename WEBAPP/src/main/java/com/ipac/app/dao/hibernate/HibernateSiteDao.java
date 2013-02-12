
package com.ipac.app.dao.hibernate;

import java.util.List;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ipac.app.dao.SiteDao;
import com.ipac.app.model.Site;
import com.ipac.app.model.hibernate.HibernateSite;


/**
 * Hibernate Implementation of Dao
 * @author RMurray
 */
@Repository("hibernateSiteDao")
public class HibernateSiteDao implements SiteDao {
    
    protected static Logger logger = Logger.getLogger("dao");
  
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    

    public List<Site> getAll() {
        
        logger.debug("Retrieving all sites");
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //Create HQL
        Query query = session.createQuery("FROM Site site order by name asc");
        
        //return full resultset
        return query.list();
        
    }    
    

    public Site getSite( Integer id ) {
        
        logger.debug("Retrieving one site id: "+id);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        return (Site) session.get(HibernateSite.class, id);
        
    }
    
    
    
}
