/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipac.app.dto;
 
import java.util.List;

import com.ipac.app.model.hibernate.HibernateInterface;




public class TeamedInterfaceDto {
    
    private Integer id;
    
    private String name;
    
    private Integer hostId;
    
    private Integer typeId;
    
    private Integer teamedInterfaceId = 0;
    
    private List<Integer> memberInterfaceIds;
    
    private List<HibernateInterface> memberInterfaces;
    
    /**
    * Checks that all the memberInterfaces are equal value. True if all equal.
    *
    * @return boolean
    */
    public Boolean getIsEqualTypeIds(){
        
        Integer total = 0;
        Integer x=1;
        
        for (HibernateInterface interfaceObj: this.memberInterfaces) {
            total = total + interfaceObj.getTypeId().intValue();
            x=interfaceObj.getTypeId().intValue();
        }
        
        if( total == x / this.memberInterfaces.size() ){
            return true;
        }else{
            return false;
        }
        
    }    

// Getter and setters:

    public List<HibernateInterface> getMemberInterfaces() {
        return memberInterfaces;
    }

    public void setMemberInterfaces(List<HibernateInterface> memberInterfaces) {
        this.memberInterfaces = memberInterfaces;
    }
    
    public List<Integer> getMemberInterfaceIds() {
        return memberInterfaceIds;
    }

    public void setMemberInterfaceIds(List<Integer> memberInterfaceIds) {
        this.memberInterfaceIds = memberInterfaceIds;
    }    

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    
    public Integer getHost() {
        return hostId;
    }
 
    public void setHost(Integer host) {
        this.hostId = host;
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
        this.name = name.toUpperCase();;
    }

    public Integer getTeamedInterfaceId() {
        return teamedInterfaceId;
    }

    public void setTeamedInterfaceId(Integer teamedInterfaceId) {
        this.teamedInterfaceId = teamedInterfaceId;
    }

}
