
package com.ipac.app.web.helpers;

import java.util.List;

import com.ipac.app.model.Host;



/**
* Hold necessary information for paged view of host list
* 
* @author RMurray
*/
public class PagedHostView {

    private NavigationInfo navInfo = new NavigationInfo();
    private List<Host> hosts;

    public NavigationInfo getNavInfo() {
        return navInfo;
    }

    public void setNavInfo(NavigationInfo navInfo) {
        this.navInfo = navInfo;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> customers) {
        this.hosts = customers;
    }

    public Host getHost(int i) {
        return (Host) hosts.get(i);
    }

    public void setHost(int i, Host customer) {
        this.hosts.add(i, customer);
    }
    
    public void setMaxListItems(int maxItems){
        this.navInfo.setPageSize(maxItems);
        this.navInfo.setMaxIndices(maxItems);
    }
}
