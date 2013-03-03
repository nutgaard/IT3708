/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.fitness;

import java.util.Properties;
import junit.framework.TestCase;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.binary.BinaryPhenoType;
import no.utgdev.ga.core.utils.TestUtils;

/**
 *
 * @author Nicklas
 */
public class BinaryFitnessHandlerTest extends TestCase {

    public BinaryFitnessHandlerTest(String testName) {
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
     * Test of generateFitnessMap method, of class BinaryFitnessHandler.
     */
    public void testGenerateFitnessMap() {
        System.out.println("generateFitnessMap");
        Properties props = new Properties();
        props.put("binary.length", "16");
        Population<BinaryPhenoType> population = TestUtils.createPopulation(new String[]{
                    "1111111111111111",
                    "1111111111111111",
                    "1111110011111100",
                    "1111000011110000",
                    "1100000011000000",
                    "0000000000000000"
                });
        double[] expResult = new double[]{1.0, 1.0, 0.75, 0.5, 0.25, 0};
        GALoop ga = new GALoop(props);
        BinaryFitnessHandler instance = new BinaryFitnessHandler(ga);
        FitnessMap result = instance.generateFitnessMap(population);
        assertEquals(population.size(), result.size());
        for (int i = 0; i < population.size(); i++) {
            assertEquals(expResult[i], result.get(population.get(i)), 0.000000000001);
        }
    }
}
