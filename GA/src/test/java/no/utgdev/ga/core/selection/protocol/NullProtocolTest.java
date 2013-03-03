/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.protocol;

import junit.framework.TestCase;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.BinaryFitnessHandler;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.utils.TestUtils;

/**
 *
 * @author Nicklas
 */
public class NullProtocolTest extends TestCase {
    
    public NullProtocolTest(String testName) {
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
     * Test of selection method, of class NullProtocol.
     */
    public void testSelection() {
        System.out.println("selection");
        Population adults = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        Population children = TestUtils.createPopulation(TestUtils.createDefaultPopulation());;
        GALoop ga = new GALoop(null);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        int retainNo = children.size();
        NullProtocol instance = new NullProtocol(ga);
        int expResult = children.size() + adults.size();
        Population result = instance.selection(adults, children, fitnessHandler, retainNo);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of generatationRatio method, of class NullProtocol.
     */
    public void testGeneratationRatio() {
        System.out.println("generatationRatio");
        GALoop ga = new GALoop(null);
        NullProtocol instance = new NullProtocol(ga);
        double expResult = 1.0;
        double result = instance.generatationRatio();
        assertEquals(expResult, result, 0.0);
    }
}
