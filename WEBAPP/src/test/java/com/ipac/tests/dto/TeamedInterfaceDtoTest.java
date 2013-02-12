/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipac.tests.dto;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipac.app.dto.TeamedInterfaceDto;
import com.ipac.app.model.hibernate.HibernateInterface;

import static org.junit.Assert.*;

/**
 *
 * @author RMurray
 */
public class TeamedInterfaceDtoTest {
    
    public TeamedInterfaceDtoTest() {
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
     * Test of getIsEqualTypeIds method, of class TeamedInterfaceDto.
     */
    @Test
    public void testGetIsEqualTypeIds() {
        System.out.println("getIsEqualTypeIds");
        TeamedInterfaceDto instance = new TeamedInterfaceDto();
        instance.setId(1);
        
        HibernateInterface int1 = new HibernateInterface();
        int1.setId(1);
        int1.setTypeId(2);
        
        HibernateInterface int2 = new HibernateInterface();
        int2.setId(2);
        int2.setTypeId(1);
        
        List<HibernateInterface> intList = new ArrayList<HibernateInterface>();
        intList.add(int1);
        intList.add(int2);
        
        instance.setMemberInterfaces(intList);
                
        
        Boolean expResult = false;
        Boolean result = instance.getIsEqualTypeIds();
        assertEquals(expResult, result);
    }

 
}
