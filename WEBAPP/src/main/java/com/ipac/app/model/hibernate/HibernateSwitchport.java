package com.ipac.app.model.hibernate;

import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.ipac.app.model.Switchport;


@Entity(name = "Switchport")
@Table(name = "SWITCHPORT")
@Audited
@AuditTable("AUD_SWITCHPORT")
public class HibernateSwitchport implements Switchport, Serializable {
    
    private static final long serialVersionUID = 1L;
 
    @Id
    @Column(name = "ID", nullable = false, unique=true)
    @Type(type = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "SWITCH_ID", nullable = false)
    private Integer switchId;
    
    @Column(name = "CHASSIS")
    private Integer chassis;
    
    @Column(name = "BLADE")
    private Integer blade;
    
    @Column(name = "PORT", nullable = false)
    private Integer port; 
    
    @Column(name = "INTERFACE_ID", nullable = false)
    private Integer interfaceId;
    
    @Version
    @Column(name = "VERSION")
    private Integer version;

    public Integer getVersion() {
        return version;
    }    

    public Integer getBlade() {
        return blade;
    }

    public void setBlade(Integer blade) {
        this.blade = blade;
    }

    public Integer getChassis() {
        return chassis;
    }

    public void setChassis(Integer chassis) {
        this.chassis = chassis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Integer interfaceId) {
        this.interfaceId = interfaceId;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getSwitchId() {
        return switchId;
    }

    public void setSwitchId(Integer switchId) {
        this.switchId = switchId;
    }
    
}
