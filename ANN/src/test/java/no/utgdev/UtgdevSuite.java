/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import no.utgdev.ann.AnnSuite;

/**
 *
 * @author Nicklas
 */
public class UtgdevSuite extends TestCase {
    
    public UtgdevSuite(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite("UtgdevSuite");
        suite.addTest(AnnSuite.suite());
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
