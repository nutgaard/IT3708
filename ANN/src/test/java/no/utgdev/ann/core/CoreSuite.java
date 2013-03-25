/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import no.utgdev.ann.core.structured.StructuredSuite;

/**
 *
 * @author Nicklas
 */
public class CoreSuite extends TestCase {
    
    public CoreSuite(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite("CoreSuite");
        suite.addTest(StructuredSuite.suite());
        suite.addTest(INeuralNetTest.suite());
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
