
package com.ipac.app.web;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import com.ipac.app.model.InterfaceIp;
import com.ipac.app.model.Subnet;
import com.ipac.app.model.Vlan;
import com.ipac.app.model.hibernate.HibernateInterfaceIp;
import com.ipac.app.model.validation.IpValidator;
import com.ipac.app.service.InterfaceIpService;
import com.ipac.app.service.InterfaceService;
import com.ipac.app.service.SubnetService;
import com.ipac.app.service.UserService;
import com.ipac.app.service.VlanService;


/**
 * Handles all /ipaddress view requests
 * @author RMurray
 */
@Controller
@RequestMapping("/ipaddress")
public class IpAddressController extends IpacWebController {
    
    @Resource(name="interfaceIpService")
    private InterfaceIpService interfaceIpService;
    
    @Resource(name="interfaceService")
    private InterfaceService interfaceService;    
    
    @Resource(name="subnetService")
    private SubnetService subnetService;   
    
    @Resource(name="vlanService")
    private VlanService vlanService; 
    
    
    /**
    * Handles and request for add interface Ip JSP page - basic
    *
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/addBasic"}, method = RequestMethod.GET, params = {"interfaceId","siteId"} )
    public String getAdd( @RequestParam("interfaceId") Integer interfaceId, @RequestParam("siteId") Integer siteId, Model model) {
        
        logger.debug("Received request to show interface IP add page for interface id: "+interfaceId);
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
              
        //create new object and add to model
        model.addAttribute("interfaceIpAttribute", new HibernateInterfaceIp());
        
        //Pass model to method to set common attributes
        model = setBasicViewModelAttrs(model, siteId);
        
        //add hostId to model
        model.addAttribute("interfaceId", interfaceId);        
        
        // This will resolve to /WEB-INF/jsp/ip_address/addBasic.jsp
        return "ip_address/addBasic";        
    }    
    
    
    /**
    * Handles POST request to add interface
    *
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/addBasic"}, method = RequestMethod.POST, params = {"interfaceId","siteId"} )
    public String postAdd( @RequestParam("interfaceId") Integer interfaceId, @RequestParam("siteId") Integer siteId, 
    		@ModelAttribute("interfaceIpAttribute") HibernateInterfaceIp interfaceIp, Model model ) {
        
        logger.debug("Received post request to add interface ip");
        
        //Get subnet obj
        Subnet subnet = subnetService.getSubnetById( interfaceIp.getSubnetId() );
        
        //Init new IpValidator
        IpValidator ipValidatorObj = new IpValidator();
        
        //Test if the IP passed is in the subnet selected
        Boolean validIpInSubnet = ipValidatorObj.isIpInSubnet(interfaceIp.getIpAddress(), subnet.getIpAddress() );
        
        InterfaceIp testInterfaceIpObj = interfaceIpService.getInterfaceIpForIpAddr(interfaceIp.getIpAddress());
        
        //If IP is not in subnet return to form
        if(validIpInSubnet == false){
            
            logger.debug("Failed to match IP address "+ interfaceIp.getIpAddress() +" to subnet "+subnet.getIpAddress());
            
            //Pass model to method to set common attributes
            model = setBasicViewModelAttrs(model, siteId);  
            
            model.addAttribute("interfaceId", interfaceId);
            
            //Attach flash msg
            model.addAttribute("flashScope.message", "Error: IP Address not in selected subnet.");
            //model.addAttribute("flashMsg", "IP Address not in selected subnet");  
            
            // Redirect to form
            return "ip_address/addBasic";
            
        }else if(testInterfaceIpObj != null){
            
            logger.debug("IP address "+ interfaceIp.getIpAddress() + " already exists in database.");
            
            //Pass model to method to set common attributes
            model = setBasicViewModelAttrs(model, siteId);
            
            model.addAttribute("interfaceId", interfaceId);
            
            //Attach flash msg  
            model.addAttribute("flashScope.message", "Error: IP Address already exists in database.");
            
            // Redirect to form
            return "ip_address/addBasic";
            
        }else{   
            
            //all ok
            
            //Add created by to obj
            interfaceIp.setCreatedBy( userService.getCurrentUsername() );
            
            // Delegate to service
            interfaceIpService.add(interfaceIp, interfaceId);
            
            Integer hostId = interfaceService.getHostIdFromInterface(interfaceId);
            
            model.addAttribute("flashScope.message", "IP address added.");
            
            return "redirect:/hosts/"+hostId;
            
        }

    }  
    
    /**
    * Handles POST request to add interface
    *
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/{interfaceIpId}/delete"}, method = RequestMethod.GET )
    public String getDelete( @PathVariable Integer interfaceIpId, Model model ) {
        
        logger.debug("Received request to delete interfaceIp id: "+interfaceIpId);
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        //Get Host ID
        InterfaceIp intIp = interfaceIpService.getInterfaceIp(interfaceIpId);
        Integer hostId = interfaceService.getHostIdFromInterface( intIp.getInterfaceId() );
        
        //delete InterfaceIp
        interfaceIpService.deleteInterfaceIp(interfaceIpId); 
        
        model.addAttribute("flashScope.message", "IP address deleted.");
        
        return "redirect:/hosts/"+hostId;
    }    
    
    
    /**
    * Handles GET request for next available IP address for subnet. Note: returns JSON
    *
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/nextAvailable.json{subnetId}"}, method = RequestMethod.GET, 
    		headers="Accept=application/xml, application/json", params = "subnetId" )
    public @ResponseBody List<String> getNext( @RequestParam("subnetId") Integer subnetId) {
        
        logger.debug("Received GET request for next available IP address for subnet id: "+subnetId);
        
        //Get the Subnet as cidr by id
        Subnet subnet = subnetService.getSubnetById(subnetId);
        
        List<String> nextIpList = interfaceIpService.getNextAvailableIpForSubnet(subnet.getIpAddress(), 1);
        
        return nextIpList;

    }    

    /**
    * Method to perform repeated task of added attributes to model for addbasic form view
    * @param model
    * @return model with added attributes
    */    
    private Model setBasicViewModelAttrs(Model model, Integer siteId){
        
        //Get all Subnets by site ID
        List<Vlan> vlanList = vlanService.getAll(siteId);
        
        //Pass siteID to view
        model.addAttribute("siteId", siteId);

        //add List of subnets to model
        model.addAttribute("vlanList", vlanList);
        return model;
    }
}
