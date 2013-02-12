
package com.ipac.app.model.hibernate;

import com.ipac.app.model.HostInterfaceIp;


public class HibernateHostInterfaceIp implements HostInterfaceIp{
    
    public Integer hostid;
    
    public String hostname;
    
    public Integer interfaceid;
    
    public String interfacename;
    
    public Integer interfaceipid;
    
    public String interfaceipdddr;

    public Integer getHostid() {
        return hostid;
    }

    public void setHostid(Integer hostid) {
        this.hostid = hostid;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getInterfaceid() {
        return interfaceid;
    }

    public void setInterfaceid(Integer interfaceid) {
        this.interfaceid = interfaceid;
    }

    public String getInterfaceipdddr() {
        return interfaceipdddr;
    }

    public void setInterfaceipdddr(String interfaceipdddr) {
        this.interfaceipdddr = interfaceipdddr;
    }

    public Integer getInterfaceipid() {
        return interfaceipid;
    }

    public void setInterfaceipid(Integer interfaceipid) {
        this.interfaceipid = interfaceipid;
    }

    public String getInterfacename() {
        return interfacename;
    }

    public void setInterfacename(String interfacename) {
        this.interfacename = interfacename;
    }
    
}
