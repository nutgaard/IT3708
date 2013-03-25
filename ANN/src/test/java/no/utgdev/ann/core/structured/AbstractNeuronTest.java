/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.structured;

import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author Nicklas
 */
public class AbstractNeuronTest extends TestCase {
    
    public AbstractNeuronTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(AbstractNeuronTest.class);
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
     * Test of addInput method, of class AbstractNeuron.
     */
    public void testAddInput() {
        System.out.println("addInput");
        Synapse as = null;
        AbstractNeuron instance = new AbstractNeuronImpl();
        instance.addInput(as);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStaticInput method, of class AbstractNeuron.
     */
    public void testSetStaticInput() {
        System.out.println("setStaticInput");
        double input = 0.0;
        AbstractNeuron instance = new AbstractNeuronImpl();
        instance.setStaticInput(input);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of activationFunction method, of class AbstractNeuron.
     */
    public void testActivationFunction() {
        System.out.println("activationFunction");
        double input = 0.0;
        AbstractNeuron instance = new AbstractNeuronImpl();
        double expResult = 0.0;
        double result = instance.activationFunction(input);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutput method, of class AbstractNeuron.
     */
    public void testGetOutput() {
        System.out.println("getOutput");
        AbstractNeuron instance = new AbstractNeuronImpl();
        double expResult = 0.0;
        double result = instance.getOutput();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class AbstractNeuron.
     */
    public void testUpdate() {
        System.out.println("update");
        AbstractNeuron instance = new AbstractNeuronImpl();
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sync method, of class AbstractNeuron.
     */
    public void testSync() {
        System.out.println("sync");
        AbstractNeuron instance = new AbstractNeuronImpl();
        instance.sync();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInputSynapses method, of class AbstractNeuron.
     */
    public void testGetInputSynapses() {
        System.out.println("getInputSynapses");
        AbstractNeuron instance = new AbstractNeuronImpl();
        List expResult = null;
        List result = instance.getInputSynapses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AbstractNeuronImpl extends AbstractNeuron {

        public double activationFunction(double input) {
            return 0.0;
        }
    }
}
