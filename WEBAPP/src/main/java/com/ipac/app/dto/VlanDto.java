/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipac.app.dto;

import java.util.List;

import com.ipac.app.model.Site;
import com.ipac.app.model.Subnet;
import com.ipac.app.model.Vlan;


/**
 * Data Transfer Object for displaying purposes, mapping subnets to vlans
 */
public class VlanDto {
    
    private Integer id;
    
    private String name;
    
    private String descr;
    
    private Integer swVlanId;
    
    private Boolean routable;  
    
    private List<Subnet> subnetList;
    
    private Site site;
    
    private String created_by;
    
    private String updated_by;

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRoutable() {
        return routable;
    }

    public void setRoutable(Boolean routable) {
        this.routable = routable;
    }

    public List<Subnet> getSubnetList() {
        return subnetList;
    }

    public void setSubnetList(List<Subnet> subnetList) {
        this.subnetList = subnetList;
    }

    public Integer getSwVlanId() {
        return swVlanId;
    }

    public void setSwVlanId(Integer swVlanId) {
        this.swVlanId = swVlanId;
    }
    
    
    
}
