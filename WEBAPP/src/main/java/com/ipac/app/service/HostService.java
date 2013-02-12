
package com.ipac.app.service;

import java.util.List;

import javax.annotation.Resource;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.ipac.app.dao.HostDao;
import com.ipac.app.dto.HostDto;
import com.ipac.app.model.Host;
import com.ipac.app.model.HostInterfaceIp;


/**
 * Service for processing Hosts
 * @author RMurray
 */
@Service("hostService")
@Transactional
public class HostService {
    
    protected static Logger logger = Logger.getLogger("service"); 
    
    @Autowired
    @Qualifier("hibernateHostDao")
    private HostDao hostDao;   
    
    @Resource(name="interfaceService")
    private InterfaceService interfaceService;  
    
    /**
    * Retrieves count of ALL hosts
    * @params -
    * @return integer
    */    
    @Transactional(readOnly = true)
    public Integer getAllHostCount(){
        return hostDao.countHosts();
    }
    
    /**
    * Retrieves count of ALL hosts
    * @params SiteID
    * @return integer
    */    
    @Transactional(readOnly = true)
    public Integer getAllHostCount(Integer siteId){
        return hostDao.countHosts(siteId);
    }
    
    /**
    * Retrieves count of ALL hosts by name - search
    * @params String searchname
    * @return integer
    */    
    @Transactional(readOnly = true)
    public Integer getAllHostCountByName(final String hostSrchStr){
        return hostDao.countHostsByName(hostSrchStr);
    }    
    
    /**
    * Retrieves ALL hosts
    * @params int page, int pageSize
    * @return a list of Hosts
    */
    @Transactional(readOnly = true)
    public List<Host> getAllHosts(int page, int pageSize) {
        return hostDao.getAll(page, pageSize);
    }
    
    /**
    * Retrieves ALL hosts by siteId
    * @params integer site, int page, int pageSize
    * @return a list of Hosts
    */
    @Transactional(readOnly = true)
    public List<Host> getAllHosts(Integer siteId, int page, int pageSize) {
        
        return hostDao.getAll(siteId, page, pageSize);
        
    }    
    
    /**
    * Retrieves ONE host
    *
    * @return a Host
    */    
    @Transactional(readOnly = true)
    public Host getHost( Integer id ) {
        
        return hostDao.getHost(id);
        
    }
    
    /**
    * Retrieves List of hosts searched by name
    * @params String hostSrchStr, int page, int pageSize
    * @return a list of Hosts
    */
    @Transactional(readOnly = true)
    public List<Host> getHostByName(final String hostSrchStr, int page, int pageSize) {
        
        return hostDao.getAllHostsByName(hostSrchStr, page, pageSize);
        
    }    
    
    /**
    * Add ONE host
    *
    * @return -
    */
    public void addHost(Host host) {
        
        hostDao.add(host);

    }    
    
    
    /**
    * Edit ONE host
    *
    * @return -
    */
    public void editHost(Host host) {
        
        hostDao.edit(host);
    }    
    

    /**
    * Delete ONE host person
    * @param id the id of the existing interface ip
    * @return -
    */
    public void deleteHost(Integer id) {
        
        hostDao.delete(id);
        
    }
    
    /**
    * Retrieves LIST of HostInterfaceIp for subnet id
    * 
    * @param subnetId int
    * @return a list of HostInterfaceIp - non-entity obj
    */ 
    @Transactional(readOnly = true)
    public List<HostInterfaceIp> getHostInterfaceIpBySubnetId( Integer subnetId){
        
        return hostDao.getHostInterfaceIpBySubnetId(subnetId);
        
    }
    
    /**
    * Retrieves one HostDto by id
    * 
    * @param hostId int
    * @return a HostDto
    */    
    @Transactional(readOnly = true)
    public HostDto getHostDto( Integer hostId ){
        
        Host host = getHost(hostId);
        
        //create new DTO
        HostDto dto = new HostDto();
        
        //Assign basic data
        dto.setId(host.getId());
        dto.setName(host.getName());
        dto.setNotes(host.getNotes()); 
        dto.setSite(host.getSite());
        dto.setDateCreated(host.getDateCreated());
        dto.setDateUpdated(host.getDateUpdated());
        dto.setCreatedBy(host.getCreatedBy());
        dto.setUpdatedBy(host.getUpdatedBy());
        
        //Get list of all interface objs for this host
        dto.setInterfaceList( interfaceService.getInterfaceDtosByHost( host.getId() ) );        
        
        
        return dto;
    }
    
        
}