
package com.ipac.app.dao.hibernate;

import org.apache.log4j.Logger;
import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ipac.app.dao.SwitchportDao;
import com.ipac.app.model.Switchport;
import com.ipac.app.model.hibernate.HibernateSwitchport;


/**
 * Hibernate Implementation of Dao
 * @author RMurray
 */
@Repository("hibernateSwitchportDao")
public class HibernateSwitchportDao implements SwitchportDao {
    
    protected static Logger logger = Logger.getLogger("dao");
  
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory; 
    
    
    public Switchport getSwitchport( Integer id ) {
        
        logger.debug("Retrieving one switchport id: "+id);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        Switchport switchport = (HibernateSwitchport) session.get(HibernateSwitchport.class, id);
        
        return switchport;
        
    }
    
    
    public Switchport getSwitchportByInterfaceId( Integer interfaceId ) {
        
        logger.debug("Retrieving switchport for interface id: "+interfaceId);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        Query query = session.createQuery("FROM Switchport WHERE interface_id=" +interfaceId);
        
        Switchport sw = (HibernateSwitchport) query.uniqueResult();
        
        return sw;
        
    }    
    
       
    public void add(Switchport switchportObj, Integer interfaceId) {
        
        logger.debug("Adding new switchport interface id: "+interfaceId);
   
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //add interface ID to obj
        switchportObj.setInterfaceId(interfaceId);
   
        // Persists to db
        session.save(switchportObj);
        
    }     
    
}
