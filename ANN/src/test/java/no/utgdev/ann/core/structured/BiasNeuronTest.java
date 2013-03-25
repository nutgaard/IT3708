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
public class BiasNeuronTest extends TestCase {
    
    public BiasNeuronTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(BiasNeuronTest.class);
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
     * Test of activationFunction method, of class BiasNeuron.
     */
    public void testActivationFunction() {
        System.out.println("activationFunction");
        double input = 0.0;
        BiasNeuron instance = new BiasNeuron();
        double expResult = 0.0;
        double result = instance.activationFunction(input);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
