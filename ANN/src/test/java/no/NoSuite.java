/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import no.utgdev.UtgdevSuite;

/**
 *
 * @author Nicklas
 */
public class NoSuite extends TestCase {
    
    public NoSuite(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite("NoSuite");
        suite.addTest(UtgdevSuite.suite());
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
