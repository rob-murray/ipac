package com.ipac.app.model;

import java.util.Date;


public interface Switch {
	
	public Integer getVersion();


    public Date getDateCreated();

    public Date getDateUpdated();

    public Integer getBladeCount();

    public void setBladeCount(Integer bladeCount);

    public String getDescr();

    public void setDescr(String descr);

    public Integer getId();

    public void setId(Integer id);

    public String getModel();

    public void setModel(String model);

    public String getName();

    public void setName(String name);

    public String getCreatedBy();

    public void setCreatedBy(String createdBy);

    public Integer getSiteId();

    public void setSiteId(Integer siteId);

}
