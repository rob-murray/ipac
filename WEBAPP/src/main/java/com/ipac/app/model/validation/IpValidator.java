
package com.ipac.app.model.validation;

import org.apache.commons.net.util.SubnetUtils;

/**
 * IP address validation utilities
 * @author rmurray
 */
public class IpValidator {
    
    
    /**
    * Checks if an IP address is in a subnet passed. Returns true if in subnet
    * 
    * @param String ip, String subnet
    * @return boolean
    */ 
    public static boolean isIpInSubnet(String ip, String subnet){
        
        SubnetUtils subnetUtilObj = new SubnetUtils( subnet );
        
        return subnetUtilObj.getInfo().isInRange( ip );
        
    }
    
    
    
}
