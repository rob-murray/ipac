
package com.ipac.app.model.hibernate;

import java.io.Serializable;
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
import com.ipac.app.model.Interface;




@Entity(name = "Interface")
@Table(name = "INTERFACE")
@Audited
@AuditTable("AUD_INTERFACE")
public class HibernateInterface implements Interface, Serializable{
    
    private static final long serialVersionUID = 1L;
 
    @Id
    @Column(name = "ID", nullable = false, unique=true)
    @Type(type = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    
    @Column(name = "NAME", nullable = false, length=25)
    private String name;
    
    @Column(name = "NOTES", nullable = false, length=255)
    private String notes = "";    
    
    @ManyToOne(targetEntity = HibernateHost.class)
    private Host host;
    
    @Column(name = "type_id", nullable = false)
    private Integer typeId;
    
    @Column(name = "teamed_interface_id", nullable = false)
    private Integer teamedInterfaceId = 0;
    
    @Column(name = "CREATED_BY", nullable = false)
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

    public Integer getVersion() {
        return version;
    }    
    
    @PrePersist
    protected void onCreate() {
        this.dateCreated = new Date();
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }    

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    
    public Host getHost() {
        return host;
    }
 
    public void setHost(Host host) {
        this.host = host;
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

    public Integer getTeamedInterfaceId() {
        return teamedInterfaceId;
    }

    public void setTeamedInterfaceId(Integer teamedInterfaceId) {
        this.teamedInterfaceId = teamedInterfaceId;
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
    
    
        

}
