
package com.ipac.app.dao.hibernate;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ipac.app.dao.VlanDao;
import com.ipac.app.model.Vlan;
import com.ipac.app.model.hibernate.HibernateVlan;


/**
 * Hibernate Implementation of Dao
 * @author RMurray
 */
@Repository("hibernateVlanDao")
public class HibernateVlanDao implements VlanDao{
    
    protected static Logger logger = Logger.getLogger("dao");
  
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    
    /**
    * Retrieves ALL Vlans
    *
    * @return a list of Vlans
    */    
    public List<Vlan> getAll() {
        
        logger.debug("Retrieving all vlans");
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //Create HQL
        Query query = session.createQuery("FROM Vlan vlan order by sw_vlan_id asc");
        
        //return full resultset
        return query.list();
        
    }    
    
    /**
    * Retrieves ALL Vlans by site id
    * @params int site
    * @return a list of Vlans
    */
    public List<Vlan> getAll(Integer siteId) {
        
        logger.debug("Retrieving all vlans by site id: "+siteId);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //Create HQL
        Query query = session.createQuery("FROM Vlan vlan WHERE site_id = :siteId order by sw_vlan_id asc");
        query.setParameter("siteId", siteId);
        
        //return full resultset
        return query.list();
        
    }    
    
    /**
    * Add ONE vlan
    *
    * @return -
    */    
    public void add(Vlan vlan) {
        
        logger.debug("Adding new vlan");
   
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
   
        // Persists to db
        session.save(vlan);
        
    }
    
    /**
    * Retrieves ONE vlan by id
    *
    * @return a Vlan
    */
    public Vlan getVlan( Integer id ){
        
        logger.debug("Retrieving one vlan id: "+id);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        Vlan vlan = (HibernateVlan) session.get(HibernateVlan.class, id);
        
        return vlan;
        
    }    
    
    /**
    * Retrieves ONE vlan for INTERFACE id
    * @params Integer interfaceId
    * @return a Vlan
    */    
    public Vlan getVlanByInterfaceId( Integer interfaceId ){
        
        logger.debug("Retrieving one vlan for interface IP id: "+interfaceId);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        String sql = "SELECT * FROM ipac.vlan WHERE vlan.id = (SELECT vlan_id FROM ipac.subnet INNER JOIN ipac.interface_ip ON (subnet.id = interface_ip.subnet_id) WHERE interface_ip.id = "+ interfaceId +");";
        SQLQuery query = session.createSQLQuery(sql).addEntity(HibernateVlan.class);        
        
        Vlan vlan = (HibernateVlan) query.uniqueResult();
        
        return vlan;
        
    }    
    
}
