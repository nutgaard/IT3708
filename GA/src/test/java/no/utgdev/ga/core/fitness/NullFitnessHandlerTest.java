/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.fitness;

import junit.framework.TestCase;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.population.GenoType;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.utils.TestUtils;

/**
 *
 * @author Nicklas
 */
public class NullFitnessHandlerTest extends TestCase {
    
    public NullFitnessHandlerTest(String testName) {
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
     * Test of generateFitnessMap method, of class NullFitnessHandler.
     */
    public void testGenerateFitnessMap() {
        System.out.println("generateFitnessMap");
        Population population = TestUtils.createPopulation(new String[]{"1111", "0011", "0000"});
        GALoop ga = new GALoop(null);
        NullFitnessHandler instance = new NullFitnessHandler(ga);
        double[] expResult = new double[]{0.0, 0.0, 0.0};
        FitnessMap result = instance.generateFitnessMap(population);
        assertEquals(population.size(), result.size());
        for (int i = 0; i < population.size(); i++) {
            assertEquals(expResult[i], result.get(population.get(i)));
        }
    }
}
