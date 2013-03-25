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
public class StructuredSuite extends TestCase {
    
    public StructuredSuite(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite("StructuredSuite");
        suite.addTest(CTRNNeuronTest.suite());
        suite.addTest(BiasNeuronTest.suite());
        suite.addTest(TestItTest.suite());
        suite.addTest(SynapseTest.suite());
        suite.addTest(NeuralLayerTest.suite());
        suite.addTest(StructuredANNTest.suite());
        suite.addTest(AbstractNeuronTest.suite());
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
}
