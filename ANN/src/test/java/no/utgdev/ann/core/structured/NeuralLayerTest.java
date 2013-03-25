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
public class NeuralLayerTest extends TestCase {
    
    public NeuralLayerTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(NeuralLayerTest.class);
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
     * Test of update method, of class NeuralLayer.
     */
    public void testUpdate() {
        System.out.println("update");
        NeuralLayer instance = null;
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sync method, of class NeuralLayer.
     */
    public void testSync() {
        System.out.println("sync");
        NeuralLayer instance = null;
        instance.sync();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNeurons method, of class NeuralLayer.
     */
    public void testGetNeurons() {
        System.out.println("getNeurons");
        NeuralLayer instance = null;
        List expResult = null;
        List result = instance.getNeurons();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class NeuralLayer.
     */
    public void testSize() {
        System.out.println("size");
        NeuralLayer instance = null;
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
