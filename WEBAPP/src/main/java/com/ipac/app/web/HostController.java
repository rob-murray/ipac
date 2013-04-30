
package com.ipac.app.web;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipac.app.dto.HostDto;
import com.ipac.app.model.Host;
import com.ipac.app.model.Site;
import com.ipac.app.model.hibernate.HibernateHost;
import com.ipac.app.service.HostService;
import com.ipac.app.service.SiteService;
import com.ipac.app.web.helpers.PagedHostView;


/**
 * Handles all /host view requests
 * @author RMurray
 */
@Controller
@RequestMapping("/hosts")
public class HostController extends IpacWebController {

    
    @Autowired
    private HostService hostService;    
    
    @Resource(name="siteService")
    private SiteService siteService;

    
    /**
     * Handles and retrieves ALL hosts and show it in a JSP page
     * 
     * @param model
     * @param page An optional parameter to set the page of which to display
     * @return the view
     */
    @RequestMapping( value={"/", ""}, method = RequestMethod.GET )
    public String getIndex( Model model, final @RequestParam(value="page", required=false) String page) {
        
        logger.debug("Received request to show host list page");
        
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        //Create new paged host view obj
        PagedHostView pageHostView = new PagedHostView();
        
        //Set the Number of rows in list
        pageHostView.setMaxListItems( getMaxListItems() );

        //get project row count
        pageHostView.getNavInfo().setRowCount( hostService.getAllHostCount() );
        
        //Set current page
        setCurrentPage(page, pageHostView);

        //assign results to pageview obj
        pageHostView.setHosts(hostService.getAllHosts(pageHostView.getNavInfo().getCurrentPage(), pageHostView.getNavInfo().getPageSize()));
        model.addAttribute("pagedHostView", pageHostView);
        
        // This will resolve to /WEB-INF/jsp/hosts/list.jsp
        return "hosts/list";
        
    }
    
    /**
     * Handles and retrieves ALL hosts for SITE and show it in a JSP page
     * 
     * @param siteId The ID of the site to display
     * @param model
     * @param page An optional parameter to set the page of which to display
     * @return the view
     */
    @RequestMapping( value={"/search"}, method = RequestMethod.GET, params = "siteId" )
    public String getSiteList(final @RequestParam("siteId") Integer siteId, Model model, final @RequestParam(value="page", required=false) String page) {
        
        logger.debug("Received request to show host list page for site id: "+siteId);
        
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        //Create new paged host view obj
        PagedHostView pageHostView = new PagedHostView();

        //get project row count
        pageHostView.getNavInfo().setRowCount( hostService.getAllHostCount(siteId) );
        
        //Set the Number of rows in list
        pageHostView.setMaxListItems( getMaxListItems() );
        
        //Set current page
        setCurrentPage(page, pageHostView);
        
        //assign results to pageview obj
        pageHostView.setHosts( hostService.getAllHosts(siteId, pageHostView.getNavInfo().getCurrentPage(), pageHostView.getNavInfo().getPageSize()) );
        //add to model
        model.addAttribute("pagedHostView", pageHostView);
        
        // This will resolve to /WEB-INF/jsp/hosts/list.jsp
        return "hosts/list";
        
    }    
    
    /**
    * Handles and retrieves ONE host and shows it in a JSP page
    * 
    * @param hostId The ID of the host to display
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/{hostId}"}, method = RequestMethod.GET )
    public String getShow(final @PathVariable Integer hostId, Model model) {
        
        logger.debug("Received request to show host page individual for host: "+hostId);
        
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        //get host data
        HostDto dto = hostService.getHostDto(hostId);

        //Attach to model
        model.addAttribute("host", dto);
        
        
        // This will resolve to /WEB-INF/jsp/hosts/show.jsp
        return "hosts/show";
        
    }    
    
    /**
    * Handles and request for add host JSP page
    *
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/add"}, method = RequestMethod.GET )
    public String getAdd(Model model) {
        
        logger.debug("Received request to show host add page");
        
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        //Get list of sites
        List<Site> siteList = siteService.getAll();
        model.addAttribute("siteList", siteList);
        
        //create new object and add to model
        model.addAttribute("hostAttribute", new HibernateHost());
        
        // This will resolve to /WEB-INF/jsp/hosts/add.jsp
        return "hosts/add";
        
    }    
    
    
    /**
    * Handles POST request to persist host
    * 
    * @param host The model object to store
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/add"}, method = RequestMethod.POST )
    public String postAdd(@ModelAttribute("hostAttribute") HibernateHost host, Model model, final RedirectAttributes redirectAttributes) {
        
        logger.debug("Received post request to add host");

        //Get site by site id selection and add to host
        Site site = siteService.getSite(host.getSiteId());
        host.setSite(site); 
        
        //Set created by to current user
        host.setCreatedBy( userService.getCurrentUsername() );
        
        // Delegate to service
        hostService.addHost(host);
        
        //Get new insert ID and view adds host
        Integer hostId = host.getId();
        
        redirectAttributes.addFlashAttribute("flashMessage", "Host added");

        // Redirect to url
        return "redirect:/hosts/"+hostId;
        
        
    }     
    
    
    /**
    * Handles and request for edit host JSP page
    * 
    * @param hostId The ID of the host to display
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/{hostId}/edit"}, method = RequestMethod.GET )
    public String getEdit(final @PathVariable Integer hostId, Model model) {
        
        logger.debug("Received request to show host edit page for id: "+hostId);
        
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        // Retrieve host by id
        Host existinghost = hostService.getHost(hostId);

    	// Add to model
    	model.addAttribute("hostAttribute", existinghost);
        
        // This will resolve to /WEB-INF/jsp/hosts/edit.jsp
        return "hosts/edit";
        
    }     
    
    
    /**
    * Handles POST request to edit host
    *
    * @param hostId The ID of the host to edit
    * @param host The model object to store
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/{hostId}/edit"}, method = RequestMethod.POST )
    public String postEdit(final @PathVariable Integer hostId, @ModelAttribute("hostAttribute") HibernateHost host, Model model, final RedirectAttributes redirectAttributes ) {
        
        logger.debug("Received post request to edit host for id: "+hostId);
        
        //set id
        host.setId(hostId);
        
        //Set updated by to current user
        host.setUpdatedBy( userService.getCurrentUsername() );
        
        // Delegate to service
        hostService.editHost(host);
        
        redirectAttributes.addFlashAttribute("flashMessage", "Host edit saved.");

        // Redirect to url
        return "redirect:/hosts/"+hostId;
        
        
    }     
    
    
    /**
    * Handles and request to delete host
    *
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/{hostId}/delete"}, method = RequestMethod.GET )
    public String getDelete(final @PathVariable Integer hostId, Model model, final RedirectAttributes redirectAttributes) {
        
        logger.debug("Received request to delete host for id: "+hostId);
        
        // Delete host by id
    	hostService.deleteHost(hostId);
        
    	redirectAttributes.addFlashAttribute("flashMessage", "Host deleted.");
        
        // Redirect to url
        return "redirect:/hosts";
        
    }    
       
    
    /**
    * Handles request to show search page
    * 
    * @param hostId The ID of the host to delete
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/search"}, method = RequestMethod.GET )
    public String setSearch( Model model ) {
        
        logger.debug("Received request to show host search page");
        
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        return "hosts/search";
        
    }    
    
    /** 
     * Handles request to do a search of hosts by host.name, takes a search string param to search for
     * 
     * @param searchStr The String to search
     * @param page An optional parameter to set the page of which to display
     * @param model
     * @return the view
     */
    @RequestMapping( value={"/search"}, method = RequestMethod.GET, params = "searchStr" )
    public String getSearch( @RequestParam("searchStr") String searchStr, @RequestParam(value="page", required=false) String page, Model model ) {
        
        logger.debug("Received request to do host search for string: "+searchStr);
        
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        //Create new paged host view obj
        PagedHostView pageHostView = new PagedHostView();
        
        //Set the Number of rows in list
        pageHostView.setMaxListItems( getMaxListItems() );        

        //get project row count
        pageHostView.getNavInfo().setRowCount( hostService.getAllHostCountByName(searchStr) );
        
        //Set current page
        setCurrentPage(page, pageHostView);
        
        //use Host service to get all
        pageHostView.setHosts( hostService.getHostByName(searchStr, pageHostView.getNavInfo().getCurrentPage(), pageHostView.getNavInfo().getPageSize()) );
        
        //add to model
        model.addAttribute("pagedHostView", pageHostView);        
        
        //add flash message to page
        model.addAttribute("flashMessage", "Showing hosts searched for with string: '"+searchStr+"'.");
        
        return "hosts/list";
        
    }

    /**
     * Set the PagedHostView current page from var page
     * 
     * @param page the page number passed from query param
     * @param pageHostView instance of the page view for this request
     * @throws NumberFormatException if page is parsed as not a valid Integer
     */
    private void setCurrentPage(String page, PagedHostView pageHostView) throws NumberFormatException {
        //Parse page int
        if (null == page){
            pageHostView.getNavInfo().setCurrentPage(0);
        }else{
            pageHostView.getNavInfo().setCurrentPage(Integer.parseInt(page));
        }
    }
    
    
    
}
