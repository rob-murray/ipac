package com.ipac.app.model;

import java.util.Date;

import com.ipac.app.model.Host;



public interface Interface {
	
	public Integer getVersion();

    public Date getDateCreated();

    public Date getDateUpdated();

    public Integer getTypeId();

    public void setTypeId(Integer typeId);
    
    public Host getHost();
 
    public void setHost(Host host);

    public Integer getId();

    public void setId(Integer id);

    public String getName();

    public void setName(String name);

    public Integer getTeamedInterfaceId();

    public void setTeamedInterfaceId(Integer teamedInterfaceId);

    public String getCreatedBy();

    public void setCreatedBy(String createdBy);

    public String getUpdatedBy();

    public void setUpdatedBy(String updatedBy);

    public String getNotes();

    public void setNotes(String notes);

}
