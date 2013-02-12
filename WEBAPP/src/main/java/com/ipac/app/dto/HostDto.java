
package com.ipac.app.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ipac.app.model.Site;
 
 
/**
 * Data Transfer Object for displaying purposes, mapping Interfaces to Hosts
 * @author RMurray
 */
public class HostDto {
    
    private static final DateFormat dateFormat = new SimpleDateFormat("EEE dd MMM yyyy HH:mm");
 
    private Integer id;
    private String name;
    private String notes;
    private Site site;
    private Date dateCreated;
    private Date dateUpdated;
    private String createdBy;
    private String updatedBy;
    
    private List<InterfaceDto> interfaceList;
    
    /**
    * Returns nicely formatted created date as string
    * @params -
    * @return String
    */    
    public String getFormattedDateCreated(){
        String date = "";
        try {
            date = dateFormat.format(dateCreated);
        } catch (IllegalArgumentException e) {
            date = "Date error";
        }
        return date;
    }
    /**
    * Returns nicely formatted updated date as string
    * @params -
    * @return String
    */    
    public String getFormattedDateUpdated(){
        String date = "";
        if(dateUpdated != null){
            try {
                date = dateFormat.format(dateUpdated);
            } catch (IllegalArgumentException e) {
                date = "Date error";
            }
        }
        return date;
        
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<InterfaceDto> getInterfaceList() {
        return interfaceList;
    }

    public void setInterfaceList(List<InterfaceDto> interfaceList) {
        this.interfaceList = interfaceList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
    

}
