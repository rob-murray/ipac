
package com.ipac.app.model.hibernate;

import java.io.Serializable;
 
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.ipac.app.model.Switch;


@Entity(name = "Switch")
@Table(name = "SWITCH")
@Audited
@AuditTable("AUD_SWITCH")
public class HibernateSwitch implements Switch, Serializable {
    
    private static final long serialVersionUID = 1L;
 
    @Id
    @Column(name = "ID", nullable = false, unique=true)
    @Type(type = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "NAME", nullable = false, length=25)
    private String name;
    
    @Column(name = "DESCR")
    private String descr;
    
    @Column(name = "MODEL", nullable = false, length=50)
    private String model;
    
    @Column(name = "BLADE_COUNT", nullable = false)
    private Integer bladeCount;
    
    @Column(name = "SITE_ID", nullable = false)
    private Integer siteId;     

    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy = "admin";
    
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

    public Integer getBladeCount() {
        return bladeCount;
    }

    public void setBladeCount(Integer bladeCount) {
        this.bladeCount = bladeCount;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }
    
    
}
