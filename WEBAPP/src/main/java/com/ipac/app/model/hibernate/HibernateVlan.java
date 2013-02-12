
package com.ipac.app.model.hibernate;

import java.beans.Transient;
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

import com.ipac.app.model.Site;
import com.ipac.app.model.Vlan;


@Entity(name = "Vlan")
@Table(name = "VLAN")
@Audited
@AuditTable("AUD_VLAN")
public class HibernateVlan implements Vlan, Serializable {
    
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
    
    @Column(name = "SW_VLAN_ID", nullable = false, unique=true)
    private Integer swVlanId;

    @ManyToOne(targetEntity = HibernateSite.class)
    private Site site;    
    
    @Column(name = "ROUTABLE")
    private Boolean routable;
    
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
    
    // Transient property to handle new host web form site id selection
    private transient Integer siteId;
    @Transient
    public Integer getSiteId() {
        return this.siteId;
    }
    @Transient
    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }    

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Integer getSwVlanId() {
        return swVlanId;
    }

    public void setSwVlanId(Integer swVlanId) {
        this.swVlanId = swVlanId;
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
