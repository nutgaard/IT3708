/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.structured;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author Nicklas
 */
public class SynapseTest extends TestCase {
    
    public SynapseTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(SynapseTest.class);
        return suite;
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getFrom method, of class Synapse.
     */
    public void testGetFrom() {
        System.out.println("getFrom");
        Synapse instance = null;
        AbstractNeuron expResult = null;
        AbstractNeuron result = instance.getFrom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTo method, of class Synapse.
     */
    public void testGetTo() {
        System.out.println("getTo");
        Synapse instance = null;
        AbstractNeuron expResult = null;
        AbstractNeuron result = instance.getTo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeight method, of class Synapse.
     */
    public void testGetWeight() {
        System.out.println("getWeight");
        Synapse instance = null;
        double expResult = 0.0;
        double result = instance.getWeight();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWeight method, of class Synapse.
     */
    public void testSetWeight() {
        System.out.println("setWeight");
        double weight = 0.0;
        Synapse instance = null;
        instance.setWeight(weight);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
