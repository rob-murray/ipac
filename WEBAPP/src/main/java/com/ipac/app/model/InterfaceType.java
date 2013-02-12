package com.ipac.app.model;

public interface InterfaceType {
	
	public Integer getVersion();

    public Boolean getIsSelectable();

    public void setIsSelectable(Boolean isSelectable);

    public String getDesc();

    public void setDesc(String desc);

    public Integer getId();

    public void setId(Integer id);

    public String getName();

    public void setName(String name);

    public Boolean getIsVirtual();

    public void setIsVirtual(Boolean isVirtual);

}
