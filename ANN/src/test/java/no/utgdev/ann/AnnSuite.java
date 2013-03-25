/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import no.utgdev.ann.core.CoreSuite;

/**
 *
 * @author Nicklas
 */
public class AnnSuite extends TestCase {
    
    public AnnSuite(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite("AnnSuite");
        suite.addTest(CoreSuite.suite());
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
