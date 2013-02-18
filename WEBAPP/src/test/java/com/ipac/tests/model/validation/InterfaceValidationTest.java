
package com.ipac.tests.model.validation;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipac.app.model.Interface;
import com.ipac.app.model.hibernate.HibernateInterface;
import com.ipac.app.model.validation.InterfaceValidation;

import static org.junit.Assert.*;

/**
 *
 * @author RMurray
 */
public class InterfaceValidationTest {
    
    public InterfaceValidationTest() {
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
     * Test of testHasMatchingInTypes method, of class InterfaceValidation.
     */
    @Test
    public void testTestHasMatchingInTypes() {
        System.out.println("Testing TestHasMatchingInTypes");
        
        Interface iObjA = new HibernateInterface();
        iObjA.setId(1);
        iObjA.setTypeId(1);
        
        Interface iObjB = new HibernateInterface();
        iObjB.setId(2);
        iObjB.setTypeId(1);   
        
        List<Interface> intfList = new ArrayList<Interface>();
        
        intfList.add(iObjA);
        intfList.add(iObjB);
        
        assertTrue(InterfaceValidation.testHasMatchingInTypes( intfList ));
        
        Interface iObjC = new HibernateInterface();
        iObjC.setId(1);
        iObjC.setTypeId(1);
        
        Interface iObjD = new HibernateInterface();
        iObjD.setId(2);
        iObjD.setTypeId(2);   
        
        List<Interface> intfListB = new ArrayList<Interface>();
        
        intfListB.add(iObjC);
        intfListB.add(iObjD);
        
        assertFalse(InterfaceValidation.testHasMatchingInTypes( intfListB ));        
    }
    

    
    
}
