/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.structured;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import no.utgdev.ann.core.structured.NeuralLayer.Type;

/**
 *
 * @author Nicklas
 */
public class StructuredANNTest extends TestCase {
    
    public StructuredANNTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(StructuredANNTest.class);
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
     * Test of addLayer method, of class StructuredANN.
     */
    public void testAddLayer() {
        System.out.println("addLayer");
        Type type = null;
        NeuralLayer layer = null;
        StructuredANN instance = new StructuredANN();
        instance.addLayer(type, layer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class StructuredANN.
     */
    public void testUpdate() {
        System.out.println("update");
        double[] input = null;
        StructuredANN instance = new StructuredANN();
        double[] expResult = null;
        double[] result = instance.update(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLayers method, of class StructuredANN.
     */
    public void testGetLayers() {
        System.out.println("getLayers");
        StructuredANN instance = new StructuredANN();
        NeuralLayer[] expResult = null;
        NeuralLayer[] result = instance.getLayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
