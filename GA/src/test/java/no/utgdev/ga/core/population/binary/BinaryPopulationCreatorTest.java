/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population.binary;

import junit.framework.TestCase;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.population.Population;

/**
 *
 * @author Nicklas
 */
public class BinaryPopulationCreatorTest extends TestCase {
    
    public BinaryPopulationCreatorTest(String testName) {
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
     * Test of create method, of class BinaryPopulationCreator.
     */
    public void testCreate() {
        System.out.println("create");
        GALoop ga = new GALoop(null);
        int count = 5;
        BinaryPopulationCreator instance = new BinaryPopulationCreator(ga);
        Population result = instance.create(count);
        assertEquals(count, result.size());
    }
}
