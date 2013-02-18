/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipac.tests.model.validation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipac.app.model.validation.IpValidator;

import static org.junit.Assert.*;

/**
 *
 * @author RMurray
 */
public class IpValidatorTest {
    
    public IpValidatorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isIpInSubnet method, of class IpValidator.
     */
    @Test
    public void testIsIpInSubnet() {
        
        final String subnet1 = "1.1.1.0/24";
        final String rangeStartIp = "1.1.1.0";
        final String rangeEndIp = "1.1.1.255";
        final String wildIp = "192.168.0.1";
        
        /**
         * Should validate last octet 1-254
         */
        for(int x = 1; x < 255; x++)
        {
        	
        	String ip = String.format("1.1.1.%d", x);
        	
        	assertTrue(IpValidator.isIpInSubnet(ip, subnet1));
        }
        
        assertTrue(!IpValidator.isIpInSubnet(rangeStartIp, subnet1));
        
        assertTrue(!IpValidator.isIpInSubnet(rangeEndIp, subnet1));
        
        assertTrue(!IpValidator.isIpInSubnet(wildIp, subnet1));
        
        
    }
}
