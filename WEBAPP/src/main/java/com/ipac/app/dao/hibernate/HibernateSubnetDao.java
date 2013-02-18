
package com.ipac.app.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ipac.app.dao.SubnetDao;
import com.ipac.app.model.Subnet;
import com.ipac.app.model.hibernate.HibernateSubnet;


/**
 * Hibernate Implementation of Dao
 * @author RMurray
 */
@Repository("hibernateSubnetDao")
public class HibernateSubnetDao implements SubnetDao {
    
    protected static Logger logger = Logger.getLogger("dao");
  
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    
    
    public List<Subnet> getAll() {
        
        logger.debug("Retrieving all subnets");
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //Create HQL
        Query query = session.createQuery("FROM Subnet subnet order by id asc");
        
        //return full resultset
        return query.list();
        
    }    
    
    
    public List<Subnet> getAll( Integer vlanId ) {
        
        logger.debug("Retrieving one subnet for vlan id: "+vlanId);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //Subnet subnet = (Subnet) session.get(Subnet.class, vlanId);
        
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM Subnet WHERE vlan.id=" +vlanId);
   
        // Retrieve all
        return  query.list();
        
    }
    
     
    public Subnet getSubnet( Integer id ){
        
        logger.debug("Retrieving one subnet id: "+id);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        Subnet subnet = (HibernateSubnet) session.get(HibernateSubnet.class, id);
        
        return subnet;
        
    }    
    
	public void add(Subnet subnet) {
		
		logger.debug("Adding new subnet");
		   
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
   
        // Persists to db
        session.save(subnet);
	}
	
    public List<String> getNextAvailableIpList(String subnet, Integer limit){
        
        logger.debug("Retrieving list of next available IP addresses for subnet IP: "+subnet);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();        
        
        List<String> interfaceIpList = new ArrayList<String>();
        
        //Call DB function nextips(net)
        String sql = "SELECT CAST(host(ipac.nextips_for('"+ subnet +"')) AS varchar) LIMIT "+limit;
        
        Query query = session.createSQLQuery(sql);
        
        interfaceIpList = query.list();
        return interfaceIpList;
        
    }	
    
}
