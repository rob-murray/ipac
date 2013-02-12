package com.ipac.app.model;

import java.util.Date;


public interface Vlan {
	
	public Integer getSiteId();

    public void setSiteId(Integer siteId);

    public Integer getVersion();

    public void setVersion(Integer version);
    
    public Date getDateCreated();

    public Date getDateUpdated();

    public String getDescr();

    public void setDescr(String descr);

    public Integer getId();

    public void setId(Integer id);

    public String getName();

    public void setName(String name);

    public Boolean getRoutable();

    public void setRoutable(Boolean routable);

    public Integer getSwVlanId();

    public void setSwVlanId(Integer swVlanId);

    public String getCreatedBy();

    public void setCreatedBy(String createdBy);

    public String getUpdatedBy();

    public void setUpdatedBy(String updatedBy);

    public Site getSite();

    public void setSite(Site site);

}
