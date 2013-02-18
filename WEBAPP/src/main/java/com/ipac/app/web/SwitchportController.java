
package com.ipac.app.web;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipac.app.model.Switch;
import com.ipac.app.model.hibernate.HibernateSwitchport;
import com.ipac.app.service.InterfaceService;
import com.ipac.app.service.SwitchService;
import com.ipac.app.service.SwitchportService;


/**
 * Handles all /switchport view requests
 * @author RMurray
 */
@Controller
@RequestMapping("/switchport")
public class SwitchportController extends IpacWebController {
    
    @Resource(name="switchportService")
    private SwitchportService switchportService;
    
    @Resource(name="interfaceService")
    private InterfaceService interfaceService;
    
    @Resource(name="switchService")
    private SwitchService switchService;    
    
    /**
    * Handles and request for connect switchport JSP page
    *
    * @param interfaceId The ID of the interface to connect
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/connect"}, method = RequestMethod.GET, params = "interfaceId" )
    public String getConnect(@RequestParam("interfaceId") Integer interfaceId, Model model) {
        
        logger.debug("Received request to show switchport connect page for interface id: "+interfaceId);
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        //create new object and add to model
        model.addAttribute("switchportAttribute", new HibernateSwitchport());
        
        //Get all Interface types
        List<Switch> switchList = switchService.getAllSwitches();
        
        //add List of interface types to model
        model.addAttribute("switchList", switchList);
        
        //TODO: testif hostid exists in DB
        //add hostId to model
        model.addAttribute("interfaceId", interfaceId);
        
        // This will resolve to /WEB-INF/jsp/interface/add.jsp
        return "switchport/connect";
        
    }    
    
    
    /**
    * Handles POST request to connect
    * 
    * @param interfaceId The ID of the interface to connect
    * @param switchport The switchport model object from form
    * @return redirect
    */
    @RequestMapping( value={"/connect"}, method = RequestMethod.POST, params = "interfaceId" )
    public String postConnect( @RequestParam("interfaceId") Integer interfaceId, @ModelAttribute("switchportAttribute") HibernateSwitchport switchport, Model model ) {
        
        logger.debug("Received post request to connect switchport to interface id: "+interfaceId);
        
        // Delegate to service
        switchportService.add(switchport, interfaceId);
        
        Integer hostId = interfaceService.getHostIdFromInterface(interfaceId);
        
        model.addAttribute("flashScope.message", "Connected switchport to interface.");

        // Redirect to url
        return "redirect:/hosts/"+hostId;
        
        
    }        
    
}
