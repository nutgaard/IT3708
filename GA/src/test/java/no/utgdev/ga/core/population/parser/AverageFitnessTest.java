/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population.parser;

import java.util.Properties;
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
public class AverageFitnessTest extends TestCase {
    
    public AverageFitnessTest(String testName) {
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
     * Test of parse method, of class AverageFitness.
     */
    public void testParse_Population_FitnessHandler() {
        System.out.println("parse");
        Population<PhenoType> p = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        GALoop ga = new GALoop(null);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        AverageFitness instance = new AverageFitness();
        Double expResult = 0.5;
        Double result = instance.parse(p, fitnessHandler);
        assertEquals(expResult, result);
    }
    public void testParse_Population_FitnessHandler_single_max() {
        System.out.println("parse_single_max");
        Properties props = new Properties();
        props.put("binary.length", "4");
        GALoop ga = new GALoop(props);
        Population<PhenoType> p = TestUtils.createPopulation(new String[]{"1111"});
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        AverageFitness instance = new AverageFitness();
        Double expResult = 1.0;
        Double result = instance.parse(p, fitnessHandler);
        assertEquals(expResult, result);
    }
    public void testParse_Population_FitnessHandler_single_min() {
        System.out.println("parse_single_min");
        Properties props = new Properties();
        props.put("binary.length", "4");
        Population<PhenoType> p = TestUtils.createPopulation(new String[]{"0000"});
        GALoop ga = new GALoop(props);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        AverageFitness instance = new AverageFitness();
        Double expResult = 0.0;
        Double result = instance.parse(p, fitnessHandler);
        assertEquals(expResult, result);
    }
    public void testParse_Population_FitnessHandler_single_half() {
        System.out.println("parse_single_max");
        Properties props = new Properties();
        props.put("binary.length", "4");
        Population<PhenoType> p = TestUtils.createPopulation(new String[]{"1100"});
        GALoop ga = new GALoop(props);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        AverageFitness instance = new AverageFitness();
        Double expResult = 0.5;
        Double result = instance.parse(p, fitnessHandler);
        assertEquals(expResult, result);
    }
}
