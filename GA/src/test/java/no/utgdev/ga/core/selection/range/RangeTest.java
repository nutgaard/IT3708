/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.range;

import junit.framework.TestCase;

/**
 *
 * @author Nicklas
 */
public class RangeTest extends TestCase {
    
    public RangeTest(String testName) {
        super(testName);
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
     * Test of isWithin method, of class Range.
     */
    public void testIsWithin() {
        System.out.println("isWithin");
        double v = 0.0;
        Range instance = new Range(0.0, 0.1);
        boolean expResult = true;
        boolean result = instance.isWithin(v);
        assertEquals(true, instance.isWithin(v));
        assertEquals(false, instance.isWithin(0.2));
        assertEquals(false, instance.isWithin(-0.1));
        
    }

    /**
     * Test of distance method, of class Range.
     */
    public void testDistance() {
        System.out.println("distance");
        double v = 0.0;
        double v2 = 0.3;
        Range instance = new Range(0.1, 0.2);
        Range instance2 = new Range(0.1, 0.2);
        double expResult = 0.1;
        double result = instance.distance(v);
        double result2 = instance2.distance(v2);
        assertEquals(expResult, result, 0.00000001);
        assertEquals(expResult, result2, 0.000000001);
    }

    

    /**
     * Test of normalizationFactor method, of class Range.
     */
    public void testNormalizationFactor() {
        System.out.println("normalizationFactor");
        double n = 0.0;
        Range instance = new Range(1.0, 2.0);
        instance.normalizationFactor(2.0);
        double start = 0.5,end = 1.0;
        assertEquals(start, instance.getStart());
        assertEquals(end, instance.getEnd());
    }
}
