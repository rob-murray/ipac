package com.ipac.app.model;

public interface Switchport {
	
	public Integer getVersion();

    public Integer getBlade();

    public void setBlade(Integer blade);

    public Integer getChassis();

    public void setChassis(Integer chassis);

    public Integer getId();

    public void setId(Integer id);

    public Integer getInterfaceId();

    public void setInterfaceId(Integer interfaceId);

    public Integer getPort();

    public void setPort(Integer port);

    public Integer getSwitchId();

    public void setSwitchId(Integer switchId);

}
