
package com.ipac.app.dao.hibernate;

import java.util.List;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ipac.app.dao.InterfaceTypeDao;
import com.ipac.app.model.InterfaceType;
import com.ipac.app.model.hibernate.HibernateInterfaceType;


/**
 * Hibernate Implementation of Dao
 * @author RMurray
 */
@Repository("hibernateInterfaceTypeDao")
public class HibernateInterfaceTypeDao implements InterfaceTypeDao {
    
    protected static Logger logger = Logger.getLogger("dao");
  
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    

    public List<InterfaceType> getAll() {
        
        logger.debug("Retrieving all interfaceTypes");
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        Query query = session.createQuery("FROM InterfaceType interfacetype order by name asc");
        
        //return full resultset
        return query.list();        
        
    }    
    
    

    public List<InterfaceType> getAll(Boolean selectableFilter) {
        
        logger.debug("Retrieving all interfaceTypes by selectable filter");
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //Create HQL
        String hql = "FROM InterfaceType interfacetype WHERE isSelectable = true order by name asc";
        Query query = session.createQuery(hql);
        
        //return full resultset
        return query.list();
        
    }   
    

    public InterfaceType getInterfaceType( Integer typeId ){
        
        logger.debug("Retrieving interfaceType single");
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        InterfaceType intType = (HibernateInterfaceType) session.get(HibernateInterfaceType.class, typeId);
        
        return intType;
        
    }    
    
}
