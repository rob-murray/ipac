
package com.ipac.app.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.hibernate.Query;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ipac.app.dao.InterfaceIpDao;
import com.ipac.app.model.InterfaceIp;
import com.ipac.app.model.hibernate.HibernateInterfaceIp;



/**
 * Hibernate Implementation of Dao
 * @author RMurray
 */
@Repository("hibernateInterfaceIpDao")
public class HibernateInterfaceIpDao implements InterfaceIpDao {
    
    protected static Logger logger = Logger.getLogger("dao");
  
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    
        
    public InterfaceIp getInterfaceIp( Integer interfaceIpId ){
        
        logger.debug("Retrieving one interface ip id: "+interfaceIpId);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        InterfaceIp interfaceIpObj = (HibernateInterfaceIp) session.get(HibernateInterfaceIp.class, interfaceIpId);
        
        return interfaceIpObj;        
        
    }
    
    
    public InterfaceIp getInterfaceIpByIntId( Integer interfaceId ) {    
    
        logger.debug("Retrieving one interface ip id: "+interfaceId);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //Create hql query
        Query query = session.createQuery("from InterfaceIp as interfaceIp where interface_id ="+ interfaceId);
        //get unique result and map to InterfaceIp object
        InterfaceIp intIp = (HibernateInterfaceIp) query.uniqueResult();
        
        return intIp;    

    }    
    
        
    public InterfaceIp getInterfaceIpForIpAddr( String ipAddress ) {
        
        logger.debug("Retrieving one interface IP obj for IP: "+ipAddress);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //Create hql query
        Query query = session.createQuery("from InterfaceIp as interfaceIp where ip_address ='"+ ipAddress+"'");
        //get unique result and map to InterfaceIp object
        InterfaceIp intIp = (HibernateInterfaceIp) query.uniqueResult();
        
        return intIp;        
    }    
    
        
    public void add(InterfaceIp interfaceIpObj) {
        logger.debug("Adding new ip address");
   
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
   
        // Persists to db
        session.save(interfaceIpObj);
        
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
    
        
    public List<InterfaceIp> getAll( Integer subnetIpAddr ) {
        
        logger.debug("Retrieving list of interface IP addresses for subnet IP: "+subnetIpAddr);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();        
        
        List<InterfaceIp> interfaceIpList = new ArrayList<InterfaceIp>();
        
        String hql = "FROM InterfaceIp WHERE subnet_id=" +subnetIpAddr+" order by ipAddress asc";
        
        Query query = session.createQuery(hql);
        
        interfaceIpList = query.list();
        
        return interfaceIpList;
        
    }
    
    
        
    public void delete(Integer interfaceIpId) {
        logger.debug("Deleting existing Interface IP id: "+interfaceIpId);
   
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
   
        // Retrieve existing obj
        InterfaceIp interfaceIp = (InterfaceIp) session.get(InterfaceIp.class, interfaceIpId);
   
        // Delete
        session.delete(interfaceIp);
        
    }    
    
    
}
