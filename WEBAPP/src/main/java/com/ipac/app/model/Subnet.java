package com.ipac.app.model;

import java.util.Date;

import com.ipac.app.model.Vlan;



public interface Subnet {
	
	public Integer getVersion();

    public Date getDateCreated();

    public Date getDateUpdated();

    public Integer getId();

    public void setId(Integer id);

    public String getIpAddress();

    public void setIpAddress(String ipAdress);

    public Vlan getVlan();

    public void setVlan(Vlan vlan);

    public String getCreatedBy();

    public void setCreatedBy(String createdBy);

    public String getUpdatedBy();

    public void setUpdatedBy(String updatedBy);

}
