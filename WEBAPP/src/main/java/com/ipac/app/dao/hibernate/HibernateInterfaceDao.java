
package com.ipac.app.dao.hibernate;

import java.util.List;

import javax.annotation.Resource;
import org.hibernate.Query;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ipac.app.dao.InterfaceDao;
import com.ipac.app.model.Interface;
import com.ipac.app.model.hibernate.HibernateInterface;



/**
 * Hibernate Implementation of Dao
 * @author RMurray
 */
@Repository("hibernateInterfaceDao")
public class HibernateInterfaceDao implements InterfaceDao {
    
    protected static Logger logger = Logger.getLogger("dao");
  
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    
    
    public Interface getInterface( Integer interfaceId ) {
        
        logger.debug("Retrieving interface by id: "+interfaceId);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //get interface obj by id
        Interface interfaceObj = (HibernateInterface) session.get(HibernateInterface.class, interfaceId);
        
        return interfaceObj;        
        
        
    }    
    
        
    public List<Interface> getAll( Integer hostId ) {
        
        logger.debug("Retrieving interfaces for host id: "+hostId);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //create hql query and get results
        Query query = session.createQuery("FROM Interface WHERE host.id=" +hostId+" order by id asc");
        List<Interface> interfaceList = query.list();        
        return interfaceList;
    }    
    
        
    public Integer getHostIdFromInterface( Integer interfaceId ) {
        
        logger.debug("Retrieving hostid from ipac.interface id: "+interfaceId);
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        String sql = "SELECT host_id FROM ipac.interface WHERE id = "+interfaceId;
        
        //return query results
        return ((Integer) session.createSQLQuery(sql).uniqueResult()).intValue();
        
    }    
    
        
    public int getInterfaceCount( Integer hostId ) {
        
        String sql = "SELECT COUNT(*) FROM ipac.interface as result WHERE host_id = "+hostId;
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        Long count = ((Number) session.createSQLQuery(sql).uniqueResult()).longValue();
        return count.intValue();
        
    }    
    
        
    public void add(Interface interfaceObj) {
        logger.debug("Adding new interface");
   
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
   
        // Persists to db
        session.save(interfaceObj);
        
    }    
    
        
    public void edit(Interface interfaceObj) {
        
        Integer interfaceId = interfaceObj.getId();
        
        logger.debug("Editing existing interface id: "+interfaceId);
   
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
   
        // Retrieve existing person via id
        Interface existingInterface = (HibernateInterface) session.get(HibernateInterface.class, interfaceId);
   
        // Assign updated values to this person
        existingInterface.setName(interfaceObj.getName());
        existingInterface.setNotes(interfaceObj.getNotes());
        existingInterface.setTypeId(interfaceObj.getTypeId());
 
        // Save updates
        session.save(existingInterface);
    }    
    
    
        
    public void delete(Integer id) {
        
        logger.debug("Deleting existing interface id: "+id);
   
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
   
        // Retrieve existing host
        Interface interf = (HibernateInterface) session.get(HibernateInterface.class, id);
   
        // Delete
        session.delete(interf);
        
    }   
    
        
    public void updateTeamedInterfaces(Integer teamedInterfaceId, Integer newTeamedId){
        
        logger.debug("Performing teaming update query by teamed id");
        
        //String for SQL query
        //Eg: UPDATE interface SET teamed_interface_id = 1 WHERE id = ANY(array[2,3]);
        String sql = "UPDATE ipac.interface SET teamed_interface_id = "+newTeamedId+" WHERE teamed_interface_id = "+teamedInterfaceId+";";        
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //Perform update
        session.createSQLQuery(sql).executeUpdate();        
        
    }    
    
        
    public void setTeamedInterfaces(String interfacesSqlStr, Integer teamedInterfaceId){
        
        logger.debug("Performing teaming update query by string");
        
        //String for SQL query
        //Eg: UPDATE interface SET teamed_interface_id = 1 WHERE id = ANY(array[2,3]);
        String sql = "UPDATE ipac.interface SET teamed_interface_id = "+teamedInterfaceId+" WHERE id = ANY(array["+interfacesSqlStr+"]);";        
        
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        
        //Perform update
        session.createSQLQuery(sql).executeUpdate();
        
    }    
    
    
}