
package com.ipac.app.dao.hibernate;

import java.util.List;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ipac.app.dao.SwitchDao;
import com.ipac.app.model.Switch;
import com.ipac.app.model.hibernate.HibernateSwitch;


/**
 * Hibernate Implementation of Dao
 * @author RMurray
 */
@Repository("hibernateSwitchDao")
public class HibernateSwitchDao implements SwitchDao {
    
    protected static Logger logger = Logger.getLogger("dao");
  
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    
   
    public List<Switch> getAll() {
        
        logger.debug("Retrieving all switches");
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //Create HQL
        Query query = session.createQuery("FROM Switch subnet order by id asc");
        
        //return full resultset
        return query.list();
        
    }    
    
     
    public Switch getSwitch( Integer id ) {
        
        logger.debug("Retrieving one switch id: "+id);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        Switch switchObj = (HibernateSwitch) session.get(HibernateSwitch.class, id);
        
        return switchObj;
        
    }    
    
}
