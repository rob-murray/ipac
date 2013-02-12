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
        System.out.println("isIpInSubnet");
        String ip = "1.1.1.1";
        String subnet = "1.1.1.1/24";

        boolean result = IpValidator.isIpInSubnet(ip, subnet);
        assertTrue(result);
    }
}
