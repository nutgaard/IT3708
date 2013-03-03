/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.range;

import java.util.Map.Entry;
import junit.framework.TestCase;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.binary.BinaryPhenoType;
import no.utgdev.ga.core.utils.TestUtils;

/**
 *
 * @author Nicklas
 */
public class RangeMapTest extends TestCase {
    
    public RangeMapTest(String testName) {
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
     * Test of findEntry method, of class RangeMap.
     */
    public void testFindEntry() {
        System.out.println("findEntry");
        double[] v = new double[]{
            0.1,
            0.3,
            0.5,
            0.7,
            0.9
        };
        Population<BinaryPhenoType> p = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        RangeMap<BinaryPhenoType> instance = new RangeMap();
        instance.put(new Range(0.0, 0.1999), p.get(0));
        instance.put(new Range(0.2, 0.3999), p.get(1));
        instance.put(new Range(0.4, 0.5999), p.get(2));
        instance.put(new Range(0.6, 0.7999), p.get(3));
        instance.put(new Range(0.8, 0.9999), p.get(4));
        BinaryPhenoType[] expResult = new BinaryPhenoType[]{
            p.get(0),
            p.get(1),
            p.get(2),
            p.get(3),
            p.get(4)
        };
        for (int i = 0; i < v.length; i++) {
            assertEquals(expResult[i], instance.findEntry(v[i]).getValue());
        }
    }
}
