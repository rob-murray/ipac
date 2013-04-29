
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipac.app.dto.VlanDto;
import com.ipac.app.model.Site;
import com.ipac.app.model.hibernate.HibernateVlan;
import com.ipac.app.service.SiteService;
import com.ipac.app.service.VlanService;


/**
 * Handles all /vlan requests
 * 
 * @author RMurray
 */
@Controller
@RequestMapping("/vlans")
public class VlanController extends IpacWebController {
     
    @Resource(name="vlanService")
    private VlanService vlanService;
    
    @Resource(name="siteService")
    private SiteService siteService;  
    
    
    /**
    * Handles and retrieves ALL vlans and show it in a JSP page
    *
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/", ""}, method = RequestMethod.GET )
    public String getList(Model model) {
        
        logger.debug("Received request to show vlan list page");
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        List<VlanDto> vlansDto = vlanService.getVlanDtoList();
        
        //add to model
        model.addAttribute("vlans", vlansDto);
        
        //add flash message to page
        model.addAttribute("flashMessage", "Showing ALL vlans.");

        
        // This will resolve to /WEB-INF/jsp/vlans/list.jsp
        return "vlans/list";
        
    }    
    
   /**
    * Handles search request and passes search results to the list view
    *
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/search"}, method = RequestMethod.GET, params = "siteId" )
    public String getListBySite(final @RequestParam("siteId") Integer siteId, Model model) {
        
        logger.debug("Received request to show vlan list page by site id: "+siteId);
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
    	// Prepare model object
    	List<VlanDto> vlansDto = vlanService.getVlanDtoList(siteId);    
        
        //add to model
        model.addAttribute("vlans", vlansDto);
        
        //add flash message to page 
        model.addAttribute("flashMessage", "Showing ALL vlans for site id: "+siteId);

        
        // This will resolve to /WEB-INF/jsp/vlans/list.jsp
        return "vlans/list";
        
    }    
    
    /**
    * Handles and request to show one vlan show page
    *
    * @param vlanId The id of the vlan to show
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/{vlanId}"}, method = RequestMethod.GET )
    public String getShow(final @PathVariable Integer vlanId, Model model) {
        
        logger.debug("Received request to show vlan id: "+vlanId);
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        //Get vlan obj
        VlanDto vlanDto = vlanService.getVlanDto(vlanId);      
        
        //add to model
        model.addAttribute("vlan", vlanDto);
        
        // This will resolve to /WEB-INF/jsp/vlans/show.jsp
        return "vlans/show";
        
    }     
    
    /**
    * Handles and request for add vlan JSP page
    *
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/add"}, method = RequestMethod.GET )
    public String getAdd(Model model) {
        
        logger.debug("Received request to show host vlan page");
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        //Get list of sites
        List<Site> siteList = siteService.getAll();
        model.addAttribute("siteList", siteList);      
        
        //create new object and add to model
        model.addAttribute("vlanAttribute", new HibernateVlan());
        
        // This will resolve to /WEB-INF/jsp/vlans/add.jsp
        return "vlans/add";
        
    }    
    
    
    /**
    * Handles POST request to persist a VLAN
    * 
    * @param vlan The VLAN model object from form
    * @return redirect
    */
    @RequestMapping( value={"/add"}, method = RequestMethod.POST )
    public String postAdd( @ModelAttribute("vlanAttribute") HibernateVlan vlan, Model model, final RedirectAttributes redirectAttributes ) {
        
        logger.debug("Received post request to add vlan");
        
        //Get site by site id selection and add to host
        Site site = siteService.getSite(vlan.getSiteId());
        vlan.setSite(site);     
        
        //Add username to created_by attr
        vlan.setCreatedBy( userService.getCurrentUsername() );
        
        // Delegate to service
        vlanService.add(vlan);
        
        //Get new insert ID and view adds vlan.
        Integer vlanId = vlan.getId();
        
        redirectAttributes.addFlashAttribute("flashMessage", "Created new VLAN");

        // Redirect to url
        return "redirect:/vlans/"+vlanId;
        
        
    }      
    
}
