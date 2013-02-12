/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipac.app.dto;

import java.util.List;

import com.ipac.app.dto.SwitchportDto;
import com.ipac.app.model.Interface;
import com.ipac.app.model.InterfaceIp;
import com.ipac.app.model.InterfaceType;
import com.ipac.app.model.Switchport;
import com.ipac.app.model.Vlan;
 

public class InterfaceDto {
    
    private Integer id;

    private Integer typeId;
    
    private String name;
    
    private String notes;
    
    private InterfaceType interfaceType;
    
    private InterfaceIp interfaceIp;
    
    private Vlan vlan;
    
    private SwitchportDto switchportDto;
    
    private Interface teamedInterface;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InterfaceType getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(InterfaceType interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public InterfaceIp getInterfaceIp() {
        return interfaceIp;
    }

    public void setInterfaceIp(InterfaceIp interfaceIp) {
        this.interfaceIp = interfaceIp;
    }

    public SwitchportDto getSwitchportDto() {
        return switchportDto;
    }

    public void setSwitchportDto(SwitchportDto switchportDto) {
        this.switchportDto = switchportDto;
    }

    public Interface getTeamedInterface() {
        return teamedInterface;
    }

    public void setTeamedInterface(Interface teamedInterface) {
        this.teamedInterface = teamedInterface;
    }

    public Vlan getVlan() {
        return vlan;
    }

    public void setVlan(Vlan vlan) {
        this.vlan = vlan;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
    
}
