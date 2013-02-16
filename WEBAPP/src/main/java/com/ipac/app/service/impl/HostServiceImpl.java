package com.ipac.app.service.impl;

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
import com.ipac.app.service.InterfaceService;
import com.ipac.app.service.HostService;

@Service("hostService")
@Transactional
public class HostServiceImpl implements HostService {
	
	protected static Logger logger = Logger.getLogger("service"); 
    
    @Autowired
    @Qualifier("hibernateHostDao")
    private HostDao hostDao;   
    
    @Resource(name="interfaceService")
    private InterfaceService interfaceService;  
       
    @Transactional(readOnly = true)
    public Integer getAllHostCount(){
        return hostDao.countHosts();
    }
       
    @Transactional(readOnly = true)
    public Integer getAllHostCount(Integer siteId){
        return hostDao.countHosts(siteId);
    }
        
    @Transactional(readOnly = true)
    public Integer getAllHostCountByName(final String hostSrchStr){
        return hostDao.countHostsByName(hostSrchStr);
    }    
    

    @Transactional(readOnly = true)
    public List<Host> getAllHosts(int page, int pageSize) {
        return hostDao.getAll(page, pageSize);
    }
    
    @Transactional(readOnly = true)
    public List<Host> getAllHosts(Integer siteId, int page, int pageSize) {
        
        return hostDao.getAll(siteId, page, pageSize);
        
    }    
    
    @Transactional(readOnly = true)
    public Host getHost( Integer id ) {
        
        return hostDao.getHost(id);
        
    }
    

    @Transactional(readOnly = true)
    public List<Host> getHostByName(final String hostSrchStr, int page, int pageSize) {
        
        return hostDao.getAllHostsByName(hostSrchStr, page, pageSize);
        
    }    
    

    public void addHost(Host host) {
        
        hostDao.add(host);

    }    
    
    
    public void editHost(Host host) {
        
        hostDao.edit(host);
    }    
    

    public void deleteHost(Integer id) {
        
        hostDao.delete(id);
        
    }
    
    @Transactional(readOnly = true)
    public List<HostInterfaceIp> getHostInterfaceIpBySubnetId( Integer subnetId){
        
        return hostDao.getHostInterfaceIpBySubnetId(subnetId);
        
    }
       
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
