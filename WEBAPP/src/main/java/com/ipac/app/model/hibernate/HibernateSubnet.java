
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

import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.ipac.app.model.Subnet;
import com.ipac.app.model.Vlan;


@Entity(name = "Subnet")
@Table(name = "SUBNET")
@Audited
@AuditTable("AUD_SUBNET")
@TypeDef(name="inet", typeClass = InetType.class)
public class HibernateSubnet implements Subnet, Serializable {
    
    private static final long serialVersionUID = 1L;
 
    @Id
    @Column(name = "ID", nullable = false, unique=true)
    @Type(type = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "IP_ADDRESS", nullable = false)
    @Type(type = "inet")
    private String ipAddress;
    
    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy = "admin";
    
    @Column(name = "UPDATED_BY")
    private String updatedBy = "admin";
    
    @ManyToOne(targetEntity = HibernateVlan.class)
    private Vlan vlan;   
    
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAdress) {
        this.ipAddress = ipAdress;
    }

    public Vlan getVlan() {
        return vlan;
    }

    public void setVlan(Vlan vlan) {
        this.vlan = vlan;
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
    
    
}
