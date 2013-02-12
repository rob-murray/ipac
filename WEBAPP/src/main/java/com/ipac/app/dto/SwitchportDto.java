/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipac.app.dto;

import com.ipac.app.model.Switch;
import com.ipac.app.model.Switchport;

public class SwitchportDto {
    
    private Integer id;
    
    private Switch switchObj;
    
    private Integer chassis;
    
    private Integer blade;
    
    private Integer port;

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

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Switch getSwitchObj() {
        return switchObj;
    }

    public void setSwitchObj(Switch switchObj) {
        this.switchObj = switchObj;
    }
    
    public String getAsString(){
        
        String switchportStr = this.switchObj.getName() + " " +  this.chassis + "/" + this.blade + "/" + this.port;
        
        return switchportStr;
    }
}
