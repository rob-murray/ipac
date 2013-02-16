
package com.ipac.app.service;

import java.util.List;
 
import com.ipac.app.model.InterfaceType;


/**
 * Service for processing Interfaces
 * @author rmurray
 */
public interface InterfaceTypeService {   
    
    /**
    * Retrieves ALL interfacetypes
    * 
    * @param onlySelectableInterfaceTypes Boolean option for returning InterfaceTypes that are selectable only. If you want
    * only selectable InterfaceTypes then pass True
    * @return a list of InterfaceTypes
    */
    public List<InterfaceType> getAll(Boolean onlySelectableInterfaceTypes);
    
    /**
    * Retrieves ONE InterfaceType by ID
    *
    * @param typeId The ID of the InterfaceType to return
    * @return The InterfaceType for the ID
    */  
    public InterfaceType getIntTypeForId( Integer typeId );
    
}
