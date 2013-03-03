/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population.parser;

import junit.framework.TestCase;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.BinaryFitnessHandler;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.utils.TestUtils;

/**
 *
 * @author Nicklas
 */
public class StandardDeviationFitnessTest extends TestCase {
    
    public StandardDeviationFitnessTest(String testName) {
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
     * Test of parse method, of class StandardDeviationFitness.
     */
    public void testParse_Population_FitnessHandler() {
        System.out.println("parse");
        Population<PhenoType> p = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        GALoop ga = new GALoop(null);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        StandardDeviationFitness instance = new StandardDeviationFitness();
        Double expResult = 0.35355;
        Double result = instance.parse(p, fitnessHandler);
        assertEquals(expResult, result, 0.00001);
    }
}
