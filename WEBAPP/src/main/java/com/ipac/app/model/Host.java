package com.ipac.app.model;

import java.util.Date;


public interface Host{
	

    public Integer getSiteId();

    public void setSiteId(Integer siteId);  

    /**
     * Returns nicely formatted created date as string
     * @params -
     * @return String
     */
    public String getFormattedDateCreated();
    
    /**
    * Returns nicely formatted updated date as string
    * 
    * @params -
    * @return String
    */     
    public String getFormattedDateUpdated();
    
    public Integer getVersion();

    public Date getDateCreated();

    public Date getDateUpdated();

    public String getNotes();

    public void setNotes(String notes);

    public Integer getId();

    public void setId(Integer id);

    public String getName();

    public void setName(String name);

    public String getCreatedBy();

    public void setCreatedBy(String createdBy);

    public String getUpdatedBy();

    public void setUpdatedBy(String updatedBy);

    public Site getSite();

    public void setSite(Site site);

}
