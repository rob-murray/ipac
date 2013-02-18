
package com.ipac.app.model.hibernate;

import java.beans.Transient;
import java.io.Serializable;
 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.annotations.Type;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.ipac.app.model.Host;
import com.ipac.app.model.Site;


@Entity(name = "Host")
@Table(name = "HOST")
@Audited
@AuditTable("AUD_HOST")
public class HibernateHost implements Host, Serializable {
 
    private static final long serialVersionUID = 1L;
    
    private static final DateFormat dateFormat = new SimpleDateFormat("EEE dd MMM yyyy HH:mm");
 
    @Id
    @Column(name = "ID")
    @Type(type = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "NAME", nullable = false, length=50, unique=true)
    private String name;
    
    @Column(name = "NOTES")
    private String notes;
    
    @ManyToOne(targetEntity = HibernateSite.class)
    private Site site;

    @Column(name = "CREATED_BY")
    private String createdBy = "admin";
    
    @Column(name = "UPDATED_BY")
    private String updatedBy = "admin";     
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED", nullable = false)
    private Date dateCreated = new Date();
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_UPDATED")
    private Date dateUpdated; 
    
    @Version
    @Column(name = "VERSION")
    private Integer version;  
    
    /**
    * Transient property to handle new host web form site id selection
    */
    private transient Integer siteId;
    @Transient
    public Integer getSiteId() {
        return this.siteId;
    }
    @Transient
    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }    
    
    @PrePersist
    protected void onCreate() {
        this.dateCreated = new Date();
    }
    
         
    public String getFormattedDateCreated(){
        String date = "";
        try {
            date = dateFormat.format(dateCreated);
        } catch (IllegalArgumentException e) {
            date = "Date error";
        }
        return date;
    }
    
    
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
    
    public Integer getVersion() {
        return version;
    }      

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
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

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    
}
