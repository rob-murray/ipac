
package com.ipac.app.web;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipac.app.model.HostInterfaceIp;
import com.ipac.app.model.Subnet;
import com.ipac.app.model.hibernate.HibernateSubnet;
import com.ipac.app.service.HostService;
import com.ipac.app.service.SubnetService;



/**
 * Handles all /subnet view requests
 * @author RMurray
 */
@Controller
@RequestMapping("/subnets")
public class SubnetController extends IpacWebController { 
    
    @Resource(name="subnetService")
    private SubnetService subnetService;    
    
    @Resource(name="hostService")
    private HostService hostService;
    
    
    /**
    * Handles and request to list subnets by VLAN id and renders as JSON
    * 
    * @param vlan the ID of the vlanId to display
    * @return a List<Subnet> for the VLAN
    */
    @RequestMapping( method = RequestMethod.GET, headers="Accept=application/xml, application/json", params = "vlanId" )
    public @ResponseBody List<Subnet> getListForVlanId( @RequestParam("vlanId") Integer vlanId ) {
        
        logger.debug("Received request to list subnet by vlan id: "+vlanId);
        
        List<Subnet> subnetList = subnetService.getSubnetsForVlanId(vlanId);
        
        return subnetList;
    }    
    
    
    /**
    * Handles and request to show subnet JSP page - displays list of all IP addresses used on this subnet
    * 
    * @param subnetId the ID of the subnet to display
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/{subnetId}"}, method = RequestMethod.GET )
    public String getShow( @PathVariable Integer subnetId, Model model ) {
        
        logger.debug("Received request to show subnet id: "+subnetId);
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        //Get subnet object and add to model
        Subnet subnet = subnetService.getSubnetById(subnetId);
        model.addAttribute("subnet", subnet);
              
        //Get List of non-entity model objects and add to model
        List<HostInterfaceIp> hostInterfaceIpList = hostService.getHostInterfaceIpBySubnetId(subnetId);
        
        model.addAttribute("interfaceList", hostInterfaceIpList);
        
        // This will resolve to /WEB-INF/jsp/subnets/show.jsp
        return "subnets/show";
    }    
    
    /**
    * Handles and request for add subnet JSP page
    *
    * @param vlanId the ID of the VLAN to relate the Subnet to
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/add"}, method = RequestMethod.GET, params = "vlanId" )
    public String getAdd( @RequestParam("vlanId") Integer vlanId, Model model ) {
        
        logger.debug("Received request to show subnet add page. Attaching to vlan id: "+vlanId);
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        //create new object and add to model
        model.addAttribute("subnetAttribute", new HibernateSubnet());
        
        model.addAttribute("vlanId", vlanId);
        
        // This will resolve to /WEB-INF/jsp/subnets/add.jsp
        return "subnets/add";
        
    }    
    
    
    /**
    * Handles POST request to persist subnet to database
    * 
    * @param vlanId the ID of the VLAN to relate the Subnet to
    * @param subnet The model object to store
    * @return redirect
    */
    @RequestMapping( value={"/add"}, method = RequestMethod.POST, params = "vlanId" )
    public String postAdd( @RequestParam("vlanId") Integer vlanId, @ModelAttribute("subnetAttribute") HibernateSubnet subnet, Model model ) {
        
        logger.debug("Received request to show subnet add page. Attaching to vlan id: "+vlanId);
        
        //add username to created by
        subnet.setCreatedBy( userService.getCurrentUsername() );
        
        // Delegate to service
        subnetService.add(subnet, vlanId);
        
        model.addAttribute("flashScope.message", "Subnet created.");

        // Redirect to url
        return "redirect:/vlans/"+vlanId;
        
        
    }      
    
}
