
package com.ipac.app.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ipac.app.web.IpacWebController;




/**
 * Controller for site root page requests
 * @author RMurray
 */
@Controller
@RequestMapping("/")
public class HomeController extends IpacWebController {  
    
    /**
    * Handles and site root
    *
    * @return the name of the JSP page
    */
    @RequestMapping( value={"/", "/index"}, method = RequestMethod.GET )
    public String getIndex(Model model) {
        
        logger.debug("Received request to show homepage");
          
        model.addAttribute("username", userService.getCurrentUsername());
        model.addAttribute("ipacVersion", this.getCurrentIPACVersion());
        
        // This will resolve to /WEB-INF/jsp/home/index.jsp
        return "home/index";
        
    }
    
    
}
