
package com.ipac.app.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipac.app.dto.TeamedInterfaceDto;
import com.ipac.app.model.Interface;
import com.ipac.app.model.InterfaceType;
import com.ipac.app.model.hibernate.HibernateInterface;
import com.ipac.app.model.validation.InterfaceValidation;
import com.ipac.app.service.InterfaceIpService;
import com.ipac.app.service.InterfaceService;
import com.ipac.app.service.InterfaceTypeService;


/**
 * Handles all /interface view requests
 * @author RMurray
 */
@Controller
@RequestMapping("/interface")
public class InterfaceController extends IpacWebController {
    
    @Resource(name="interfaceService")
    private InterfaceService interfaceService;
    
    @Resource(name="interfaceIpService")
    private InterfaceIpService interfaceIpService;    
    
    @Resource(name="interfaceTypeService")
    private InterfaceTypeService interfaceTypeService;

    
    /**
    * Handles and request for add interface JSP page
    * 
    * @param hostId The ID of the host to add an interface to
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/add"}, method = RequestMethod.GET, params = "hostId" )
    public String getAdd( @RequestParam("hostId") Integer hostId, Model model) {
        
        logger.debug("Received request to show interface add page for host id: "+hostId);
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        //create new object and add to model
        model.addAttribute("interfaceAttribute", new HibernateInterface());
        
        //Get all Interface types
        List<InterfaceType> intTypeList = interfaceTypeService.getAll(true);
        
        //add List of interface types to model
        model.addAttribute("intTypeList", intTypeList);
        
        //TODO: testif hostid exists in DB
        //add hostId to model
        model.addAttribute("hostId", hostId);
        
        // This will resolve to /WEB-INF/jsp/interface/add.jsp
        return "interface/add";
        
    }    
    
    
    /**
    * Handles POST request to add interface
    * 
    * @param hostId The ID of the host to add an interface to
    * @param interfaceAttr The model representation from form
    * @return redirect
    */
    @RequestMapping( value={"/add"}, method = RequestMethod.POST, params = "hostId" )
    public String postAdd( @RequestParam("hostId") Integer hostId, @ModelAttribute("interfaceAttribute") HibernateInterface interfaceAttr, Model model ) {
        
        logger.debug("Received post request to add interace to host id: "+hostId);
        
        //Set created by attr to model
        interfaceAttr.setCreatedBy( userService.getCurrentUsername() );
        
        // Delegate to service
        interfaceService.add(hostId, interfaceAttr);
        
        model.addAttribute("flashScope.message", "Interface added to host.");

        // Redirect to url
        return "redirect:/hosts/"+hostId;
        
        
    }    
    
    
    /**
    * Handles and request for edit interface JSP page
    * 
    * @param interfaceId The ID of the interface to edit
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/{interfaceId}/edit"}, method = RequestMethod.GET )
    public String getEdit( @PathVariable Integer interfaceId, Model model) {
        
        logger.debug("Received request to show interface edit page for id: "+interfaceId);
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        // Retrieve interface by id
    	Interface existingInterface = interfaceService.getInterfaceById(interfaceId);

    	// Add to model
    	model.addAttribute("interfaceAttribute", existingInterface);
        model.addAttribute("interfaceId", interfaceId);
        
        //Get all Interface types
        List<InterfaceType> intTypeList = interfaceTypeService.getAll(true);
        
        //add List of interface types to model
        model.addAttribute("intTypeList", intTypeList);        
        
        // This will resolve to /WEB-INF/jsp/interface/edit.jsp
        return "interface/edit";
        
    }     
    
    
    /**
    * Handles POST request to edit interface
    *
    * @param interfaceId The ID of the interface to edit
    * @param interfaceAttr The model representation from form
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/{interfaceId}/edit"}, method = RequestMethod.POST )
    public String postEdit( @PathVariable Integer interfaceId, @ModelAttribute("interfaceAttribute") HibernateInterface interfaceObj, Model model ) {
        
        logger.debug("Received post request to edit interface id: "+interfaceId);
        
        //set id
        interfaceObj.setId(interfaceId);
        
        //Set updated by to model
        interfaceObj.setUpdatedBy( userService.getCurrentUsername() );
        
        // Delegate to service
        interfaceService.edit(interfaceObj);
        
        Integer hostId = interfaceService.getHostIdFromInterface(interfaceId);
        
        model.addAttribute("flashScope.message", "Interface edit saved.");

	// Redirect to url
        return "redirect:/hosts/"+hostId;
        
        
    }
    
    /**
    * Handles GET request to delete interface
    * 
    * @param interfaceId The ID of the interface to edit
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/{interfaceId}/delete"}, method = RequestMethod.GET )
    public String postDelete( @PathVariable Integer interfaceId, Model model ) {
        
        logger.debug("Received request to delete interface id: "+interfaceId);
        
        //Get hostId for returned view
        Integer hostId = interfaceService.getHostIdFromInterface(interfaceId);
        
        //Get teamed interface ID
        Integer teamedInterfaceIdInt = getTeamedInterfaceId();        
        
        //Get interface obj
        Interface interfaceObj = interfaceService.getInterfaceById(interfaceId);
        
        //Check if interface is teamed type
        if(interfaceObj.getTypeId().intValue() == teamedInterfaceIdInt){
            
            logger.debug("Found teamed interface. Removing teamed data from other interfaces");
            
            //remove teaming data from other interfaces
            interfaceService.setUpdatedTeamedInfo(interfaceObj.getId(), 0);
            
            //delete interface
            interfaceService.delete(interfaceObj);
            
            model.addAttribute("flashScope.message", "Interface deleted and teamed interfaces updated.");
            
        }else{
            //Check interface is not part of a team
            if(interfaceObj.getTeamedInterfaceId() == 0){
            
                logger.debug("Interface OK to delete.");
                
                interfaceService.delete(interfaceObj);
                
                model.addAttribute("flashScope.message", "Interface deleted.");

            }else{
                logger.debug("Error: Inteface is part of team. Unabled to proceed.");

                //Error: interface is part of a team so pass error to view
                model.addAttribute("flashScope.message", "Error: This interface is part of a team. Cannot delete.");
            }
        }
        
        
        return "redirect:/hosts/"+hostId;
    }    

    
    /**
    * Handles GET request for team interface JSP page
    * 
    * @param hostId The ID of the host having these interfaces to allow user to choose
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/team"}, method = RequestMethod.GET, params = "hostId" )
    public String getTeam( @RequestParam("hostId") Integer hostId, Model model) {
        
        logger.debug("Received request to show interface team page for host id: "+hostId);
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        //Validation obj to check interface list
        InterfaceValidation interfaceValidator = new InterfaceValidation();
        
        //Get teamed interface ID
        Integer teamedInterfaceIdInt = getTeamedInterfaceId();
        
        //add hostId to model
        model.addAttribute("hostId", hostId);
        
        //Create new Dto and add to model
        TeamedInterfaceDto teamedInterfaceDto = new TeamedInterfaceDto();
        model.addAttribute("interfaceAttr", teamedInterfaceDto);
        
        //get all interfaces for this host into List
        List<Interface> interfaceList = interfaceService.getInterfacesForHost(hostId);
        
        // Check there are more than one interface for this host
        if( interfaceList.size() > 1 && interfaceValidator.testHasMatchingInTypes( interfaceList ) == true ){
            
            Map<Integer,String> interfaceMap = new HashMap<Integer,String>();

            //Loop through list of interfaces to assign the interface ID and interface NAME to hashmap
            for (Interface interfaceObj: interfaceList) {
                // TODO : ONLY show available interfaces if the type is not already teamed or Virtual
                if(interfaceObj.getTypeId().intValue() != teamedInterfaceIdInt ){
                    interfaceMap.put(interfaceObj.getId(), interfaceObj.getName());
                }
            }

            //add interface list to model
            model.addAttribute("interfaceMap", interfaceMap);

            // This will resolve to /WEB-INF/jsp/interface/team.jsp
            return "interface/team";
            
        }else{
            
            logger.debug("Failed interfaceList.size() > 1 || interfaceValidator.testHasMatchingInTypes( interfaceList ) == true");
            model.addAttribute("flashScope.message", "Error: not enough interfaces to team.");
            return "redirect:/hosts/"+hostId;
            
        }
        
    }    

     
    
    
    /**
     * Handles POST to team a selection of interfaces for one host
     * 
     * @param hostId The ID of the host having these interfaces to allow user to choose
     * @param teamedInterfaceDto The DTO for a teamed interface, basically a list of interface IDs
     * @param model
     * @return redirect
     */
    @RequestMapping( value={"/team"}, method = RequestMethod.POST, params = "hostId" )
    public String setTeam( @RequestParam("hostId") Integer hostId, @ModelAttribute("interfaceAttr") TeamedInterfaceDto teamedInterfaceDto, Model model ) {
        
        logger.debug("Received post request to team interaces hostid: "+hostId);
        
        Boolean errorFlag = false;
        //Get teamed interface ID
        Integer teamedInterfaceIdInt = getTeamedInterfaceId();        
        
        //Check that there are more than 1 interfaces selected
        if( teamedInterfaceDto.getMemberInterfaceIds().size() < 2 ){
            
            logger.debug("Error: the selected intefaces count is less than 2");
            
            //Set error message
            model.addAttribute("flashScope.message", "Error: You have not selected enough interfaces to team.");
            
        }else{
            
            //Loop through to check if we have IP address for each interface or is already teamed
            //TODO: DB intensive, can be improved
            Iterator<Integer> iterator = null;
            for (iterator = teamedInterfaceDto.getMemberInterfaceIds().iterator(); iterator.hasNext(); ) {
                //Check if is part of a team
                if( interfaceService.getInterfaceById( iterator.next() ).getTeamedInterfaceId().intValue() != 0 ){
                    errorFlag = true;
                }
                //Check if has an ip address
                if(interfaceIpService.getIntIpForId( iterator.next() ) != null){
                    errorFlag = true;
                }
            }
            
            //Check for any errors
            if(errorFlag == true){
                logger.debug("Error: at least one of these interfaces has a problem and cannot proceed with teaming.");

                model.addAttribute("flashScope.message", "Error: at least one of these interfaces has a problem and cannot proceed with teaming.");
            }else{
                //Set the type ID to be the config teamedInterfaceId
                teamedInterfaceDto.setTypeId( teamedInterfaceIdInt );
                teamedInterfaceDto.setHost(hostId);
                
                //Set updated by attr to model TODO

                // Delegate to service
                interfaceService.teamInterfaces(teamedInterfaceDto);  

                model.addAttribute("flashScope.message", "Teamed interface created.");
            }              
            
        }
        // Redirect to url
        return "redirect:/hosts/"+hostId;
    }    
    
    
}
