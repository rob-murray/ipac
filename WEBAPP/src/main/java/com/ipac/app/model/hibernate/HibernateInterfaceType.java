
package com.ipac.app.model.hibernate;

import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.ipac.app.model.InterfaceType;


@Entity(name ="InterfaceType")
@Table(name = "INTERFACE_TYPE")
@Immutable
@Audited
@AuditTable("AUD_INTERFACE_TYPE")
public class HibernateInterfaceType implements InterfaceType, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID", nullable = false, unique=true)
    @Type(type = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "NAME", nullable = false, length=25)
    private String name;
    
    @Column(name = "DESCR")
    private String desc;
    
    @Column(name = "IS_VIRTUAL", nullable = false)
    private Boolean isVirtual;
    
    @Column(name = "IS_SELECTABLE", nullable = false)
    private Boolean isSelectable;
    
    @Version
    @Column(name = "VERSION")
    private Integer version;

    public Integer getVersion() {
        return version;
    }    

    public Boolean getIsSelectable() {
        return isSelectable;
    }

    public void setIsSelectable(Boolean isSelectable) {
        this.isSelectable = isSelectable;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Boolean getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Boolean isVirtual) {
        this.isVirtual = isVirtual;
    }

    
    
}
