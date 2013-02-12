package com.ipac.app.model;

/**
 * Reference obj for hibernate query aliasing
 * @author RMurray
 */
public interface HostInterfaceIp {
	
	public Integer getHostid();

    public void setHostid(Integer hostid);

    public String getHostname();

    public void setHostname(String hostname);

    public Integer getInterfaceid();

    public void setInterfaceid(Integer interfaceid);

    public String getInterfaceipdddr();

    public void setInterfaceipdddr(String interfaceipdddr);

    public Integer getInterfaceipid();

    public void setInterfaceipid(Integer interfaceipid);

    public String getInterfacename();

    public void setInterfacename(String interfacename);

}
