
package com.ipac.app.model.validation;

import java.util.HashMap;
import java.util.List;

import com.ipac.app.model.Interface;
import com.ipac.app.model.InterfaceType;

/**
 * Validates obj Interfaces
 * @author RMurray
 */
public class InterfaceValidation {
    
    
    /**
    * Tests the List of interfaces contains at least 2 matching interface types
    * @params List<Interface> interfaceList
    * @return Boolean true if has > 2 matching interface types
    */    
    public static Boolean testHasMatchingInTypes(List<Interface> interfaceList){
        
        HashMap<Integer, Integer> frequencymap = new HashMap<Integer, Integer>();
        Integer maximum = 0;

        //Loop through interface list
        for(Interface interfaceObj : interfaceList) {
            //If the hashmap contains the intType then increase count
            if(frequencymap.containsKey(interfaceObj.getTypeId())) {
                frequencymap.put(interfaceObj.getTypeId(), frequencymap.get(interfaceObj.getTypeId())+1);
            }else{
                //add interface type with count of 1
                frequencymap.put(interfaceObj.getTypeId(), 1);
            }
        }
        
        //Loop through hashmap to find the maximum count
        for (Integer value : frequencymap.values()) {
            if(value > maximum){
                maximum = value;
            }    
        }    

        //If the max count > 1 then we have matching interface types
        if(maximum > 1){
            return true;
        }else{
            return false;
        }        
    }
    
}
