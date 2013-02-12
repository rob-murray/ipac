package com.ipac.app.model;

import java.util.Date;


public interface Site {
	
	public Integer getVersion();

    public String getCreatedBy();

    public void setCreatedBy(String createdBy);

    public Date getDateCreated();

    public void setDateCreated(Date dateCreated);

    public Date getDateUpdated();

    public void setDateUpdated(Date dateUpdated);

    public Integer getId();

    public void setId(Integer id);

    public String getName();

    public void setName(String name);

    public String getNotes();

    public void setNotes(String notes);

    public String getUpdatedBy();

    public void setUpdatedBy(String updatedBy);

}
