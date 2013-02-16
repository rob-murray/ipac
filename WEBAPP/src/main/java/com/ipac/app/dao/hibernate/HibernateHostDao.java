
package com.ipac.app.dao.hibernate;


import java.util.List;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.ipac.app.dao.HostDao;
import com.ipac.app.model.Host;
import com.ipac.app.model.HostInterfaceIp;
import com.ipac.app.model.hibernate.HibernateHost;
import com.ipac.app.model.hibernate.HibernateHostInterfaceIp;


/**
 * Hibernate Implementation of Dao
 * 
 * @author RMurray
 */
@Repository("hibernateHostDao")
public class HibernateHostDao implements HostDao{
    
    protected static Logger logger = Logger.getLogger("dao");
  
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
       
    public Integer countHosts(){
        Session session = sessionFactory.getCurrentSession();
        Long i = (Long) session.createCriteria(HibernateHost.class).setProjection(Projections.rowCount()).uniqueResult();
        return i.intValue();
    }
       
    public Integer countHosts(Integer siteId){
        String sql = "SELECT COUNT(*) FROM ipac.host as result WHERE site_id = "+siteId;
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Long i = ((Number) session.createSQLQuery(sql).uniqueResult()).longValue();
        return i.intValue();
    }  
    
   
    public Integer countHostsByName(String hostSrchStr){
        //TODO: SANITIZE string input
        String sql = "SELECT COUNT(*) FROM ipac.host as result WHERE name LIKE upper('%"+hostSrchStr+"%')";
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Long i = ((Number) session.createSQLQuery(sql).uniqueResult()).longValue();
        return i.intValue();
    }
    
    
    public List<Host> getAll(int page, int pageSize){
        
        logger.debug("Retrieving all hosts");
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //Create HQL
        Query query = session.createQuery("FROM Host host order by name asc");
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);        
        
        //return resultset
        return query.list();        
          
    }
    
    
    public List<Host> getAll(final Integer siteId, int page, int pageSize) {
        
       logger.debug("Retrieving all hosts by site id: "+siteId+" page: "+page+" pageSize: "+pageSize);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //Create HQL
        Query query = session.createQuery("FROM Host host WHERE site_id = :siteId order by name asc");
        query.setParameter("siteId", siteId);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        
        //return full resultset
        return query.list();
        
    }
    
    
    public List<Host> getAllHostsByName(String hostSrchStr) {
        
        logger.debug("Retrieving hosts by string: "+hostSrchStr);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        Query query = session.createQuery("FROM Host host WHERE UPPER(name) LIKE UPPER(:hostSrchStr) ORDER BY name ASC");
        query.setParameter("hostSrchStr", "%"+hostSrchStr + "%");
        
        return query.list();  
        
    }
    
        
    public List<Host> getAllHostsByName(String hostSrchStr, int page, int pageSize) {
        
        logger.debug("Retrieving hosts by string: "+hostSrchStr);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //Create HQL
        Query query = session.createQuery("FROM Host host WHERE UPPER(name) LIKE UPPER(:hostSrchStr) ORDER BY name ASC");
        query.setParameter("hostSrchStr", "%"+hostSrchStr + "%");
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);        
        
        return query.list();        
        
    }    
    
       
    public Host getHost(final Integer id ) {
        
        logger.debug("Retrieving one host id: "+id);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        Host host = (HibernateHost) session.get(HibernateHost.class, id);
        
        return host;        
        
        
    }
    
       
    public void add(Host host) {
        
        logger.debug("Adding new host");
   
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
   
        // Persists to db
        session.save((HibernateHost)host);
        
    }
    
        
    public void edit(Host host) {
        
        logger.debug("Editing existing host");
   
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
   
        // Retrieve existing host via id
        Host existingHost = (HibernateHost) session.get(HibernateHost.class, host.getId());
   
        // Assign updated values to this host
        existingHost.setName(host.getName());
        existingHost.setNotes(host.getNotes());
        existingHost.setUpdatedBy(host.getUpdatedBy());
 
        // Save updates
        session.save(existingHost);        
        
    }
    
        
    public void delete(Integer id) {
        
        logger.debug("Deleting existing host id: "+id);
   
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
   
        // Retrieve existing host
        Host host = (HibernateHost) session.get(HibernateHost.class, id);
   
        // Delete
        session.delete(host);
        
    }    
    
        
    public List<HostInterfaceIp> getHostInterfaceIpBySubnetId( Integer subnetId){
        
        logger.debug("Retrieving hostInterfaceIp list for subnet id: "+subnetId);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
String sql = "SELECT "+
  "interface_ip.id AS interfaceIpId,"+
  "CAST(interface_ip.ip_address AS varchar) AS interfaceipdddr, "+     
  "interface.id AS interfaceId,"+
  "interface.name AS interfaceName,"+
  "host.id AS hostId, "+
  "host.name AS hostName "+
"FROM "+
  "ipac.host, "+
  "ipac.interface, "+
  "ipac.interface_ip "+
"WHERE "+
  "interface.host_id = host.id AND "+
  "interface_ip.interface_id = interface.id AND "+
  "interface_ip.subnet_id = "+subnetId+
" ORDER BY interface_ip.ip_address ASC";
        
        //Create new SQL query and set Transformer to the non-entity model object HostInterfaceIp
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(HibernateHostInterfaceIp.class));        
        return query.list();        
        
    }
    
}
