
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
import org.hibernate.annotations.TypeDef;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.ipac.app.model.InterfaceIp;


@Entity(name = "InterfaceIp")
@Table(name = "INTERFACE_IP")
@Audited
@AuditTable("AUD_INTERFACE_IP")
@TypeDef(name="inet", typeClass = InetType.class)
public class HibernateInterfaceIp implements InterfaceIp, Serializable {
    
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
    
    @Column(name = "INTERFACE_ID", nullable = false)
    private Integer interfaceId;
    
    @Column(name = "SUBNET_ID", nullable = false)
    private Integer subnetId;
    
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

    public Integer getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(Integer subnetId) {
        this.subnetId = subnetId;
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

    public Integer getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Integer interfaceId) {
        this.interfaceId = interfaceId;
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
