package com.ipac.app.model;

import java.util.Date;

public interface InterfaceIp {
	
	public Date getDateCreated();

    public Date getDateUpdated();

    public Integer getSubnetId();

    public void setSubnetId(Integer subnetId);

    public Integer getId();

    public void setId(Integer id);

    public String getIpAddress();

    public void setIpAddress(String ipAdress);

    public Integer getInterfaceId();

    public void setInterfaceId(Integer interfaceId);

    public String getCreatedBy();

    public void setCreatedBy(String createdBy);

    public String getUpdatedBy();

    public void setUpdatedBy(String updatedBy);

}
