package com.ipac.app.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipac.app.dao.InterfaceDao;
import com.ipac.app.dto.InterfaceDto;
import com.ipac.app.dto.SwitchportDto;
import com.ipac.app.dto.TeamedInterfaceDto;
import com.ipac.app.model.Host;
import com.ipac.app.model.Interface;
import com.ipac.app.model.InterfaceIp;
import com.ipac.app.model.InterfaceType;
import com.ipac.app.model.hibernate.HibernateInterface;
import com.ipac.app.service.HostService;
import com.ipac.app.service.InterfaceIpService;
import com.ipac.app.service.InterfaceService;
import com.ipac.app.service.InterfaceTypeService;
import com.ipac.app.service.SwitchportService;
import com.ipac.app.service.VlanService;


@Service("interfaceService")
@Transactional
public class InterfaceServiceImpl implements InterfaceService {
	
    protected static Logger logger = Logger.getLogger("service");

    @Autowired
    private InterfaceDao interfaceDao;
    
    @Resource(name="interfaceTypeService")
    private InterfaceTypeService interfaceTypeService;    
    
    @Resource(name="interfaceIpService")
    private InterfaceIpService interfaceIpService;  
    
    @Resource(name="switchportService")
    private SwitchportService switchportService;
    
    @Resource(name="vlanService")
    private VlanService vlanService;  
    
    @Resource(name="hostService")
    private HostService hostService;
    
    @Transactional(readOnly = true)
    public List<InterfaceDto> getInterfaceDtosByHost( Integer hostId ) {
        
        logger.debug("Retrieving interface Dtos for host id: "+hostId);
        
        //get interface list
        List<Interface> interfaceList = getInterfacesForHost(hostId);
        
        //prepare holder for model objs
        List<InterfaceDto> interfacesDto = new ArrayList<InterfaceDto>();
        
        //Loop through all interfaces
        for (Interface interfaceObj: interfaceList) {

            // Add to model list
            interfacesDto.add( prepareInterfaceDto( interfaceObj ) );
        }
        //interfacesDto.
        return interfacesDto;
        
    }
    

    @Transactional(readOnly = true)
    private InterfaceDto prepareInterfaceDto( Interface interfaceObj ){
        
        // Create new data transfer object
        InterfaceDto dto = new InterfaceDto();
            
        //assign atts
        dto.setId( interfaceObj.getId() );
        dto.setName( interfaceObj.getName() );
        dto.setNotes( interfaceObj.getNotes() );
        dto.setTypeId( interfaceObj.getTypeId() );
          
        //set the interface type to an obj for that ref
        InterfaceType interfaceType = interfaceTypeService.getIntTypeForId( interfaceObj.getTypeId() );
        dto.setInterfaceType( interfaceType );
           
        //Set the teamed interface as the teamed inteface object
        dto.setTeamedInterface( getInterfaceById(interfaceObj.getTeamedInterfaceId()) );
            
        //get the IP address for this interface
        InterfaceIp interfaceIp = interfaceIpService.getIntIpForId( interfaceObj.getId() );
        dto.setInterfaceIp( interfaceIp );
            
        //Set dto VLAN for this interface IP id
        if(interfaceIp != null){
            dto.setVlan( vlanService.getVlanByInterfaceId( interfaceIp.getId() ) );
        }  
            
        //If the Interface type is physical
        if(interfaceType.getIsVirtual() == false){
            //get the switchport this interface is connected to
            SwitchportDto swDto = switchportService.getSwitchportDtoByInterfaceId( interfaceObj.getId() );
            dto.setSwitchportDto( swDto );
        }        
        return dto;
    }
        
    
 
    @Transactional(readOnly = true)
    public Interface getInterfaceById( Integer interfaceId ) {
                
        return interfaceDao.getInterface(interfaceId);
        
    }    
    

    @Transactional(readOnly = true)
    public Integer getHostIdFromInterface( Integer interfaceId ) {
        
        return interfaceDao.getHostIdFromInterface(interfaceId);
        
    }  
    

    @Transactional(readOnly = true)
    public List<Interface> getInterfacesForHost( Integer hostId ) {
        
        return interfaceDao.getAll(hostId);
    }
    
    

    public void add(Integer hostId, Interface interfaceObj) {
        logger.debug("Adding new interface to host id: "+hostId);
   
        // Retrieve existing host via id
        Host existingHost = hostService.getHost(hostId);
   
        // Add host to interface
        interfaceObj.setHost(existingHost);
   
        interfaceDao.add(interfaceObj);
        
    }    
    

    public void edit(Interface interfaceObj) {
        
        logger.debug("Editing existing interface id: "+interfaceObj.getId());
        
        interfaceDao.edit(interfaceObj);
    }
    

    public void delete(Interface interfaceObj) {
        
        logger.debug("Deleting interface id: "+interfaceObj.getId());
        
        interfaceDao.delete( interfaceObj.getId() );
    }    
    
    

    public void teamInterfaces(TeamedInterfaceDto teamedInterfaceDto){
        
        logger.debug("Request to create teamed interface");
        
        //Create new Interface
        Interface newInterface = new HibernateInterface();
        newInterface.setName(teamedInterfaceDto.getName());
        newInterface.setTypeId(teamedInterfaceDto.getTypeId());
        newInterface.setTeamedInterfaceId(teamedInterfaceDto.getTeamedInterfaceId());
        
        //Create a new interface for this new teamed interface
        add(teamedInterfaceDto.getHost(), newInterface);
        
        //Get the ID of the new interface
        Integer newInterfaceId = newInterface.getId();

        //Create a List of Integers to hold the IDs of the selected interfaces to be teamed
        List<Integer> intList = new ArrayList<Integer>();
        
        //Loop through the selected interfaces adding to intList above
        Iterator<Integer> iterator = null;
        for (iterator = teamedInterfaceDto.getMemberInterfaceIds().iterator(); iterator.hasNext(); ) {
            intList.add( iterator.next() );
        }        
        
        //perform the teaming update to other interfaces
        setTeamedInfo( intList, newInterfaceId );

    }
    
  
    public void setUpdatedTeamedInfo(Integer teamedInterfaceId, Integer newTeamedId){
        
        logger.debug("Constructing sql & exec statement for teamed id mode teaming");
        
        interfaceDao.updateTeamedInterfaces(teamedInterfaceId, newTeamedId);
    }    
    

    public void setTeamedInfo(List<Integer> interfaceList, Integer teamedInterfaceId){
        
        logger.debug("Constructing sql & exec statement for list mode teaming");
        
        //String to hold interface ids to be teamed
        String interfacesSqlStr = "";
        
        //Loop through List of Integers to create string for selected interfaces
        for(int i=0; i<interfaceList.size(); i++){
            if(i < interfaceList.size()-1){
                interfacesSqlStr += interfaceList.get(i).toString() + ", ";
            }else{
                interfacesSqlStr += interfaceList.get(i).toString();
            }
        }

        //Perform update
        interfaceDao.setTeamedInterfaces(interfacesSqlStr, teamedInterfaceId);
        
    }    

}
