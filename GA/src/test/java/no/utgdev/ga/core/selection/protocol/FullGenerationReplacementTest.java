/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.protocol;

import junit.framework.TestCase;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.BinaryFitnessHandler;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.utils.TestUtils;

/**
 *
 * @author Nicklas
 */
public class FullGenerationReplacementTest extends TestCase {

    public FullGenerationReplacementTest(String testName) {
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
     * Test of selection method, of class FullGenerationReplacement.
     */
    public void testSelection() {
        System.out.println("selection");
        Population adults = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        Population<PhenoType> children = TestUtils.createPopulation(new String[]{
                    "00000000",
                    "00000000",
                    "00000000",
                    "00000000",
                    "00000000"
                });
        GALoop ga = new GALoop(null);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        int retainNo = 5;
        FullGenerationReplacement instance = new FullGenerationReplacement(ga);
        Population expResult = children;
        Population<PhenoType> result = instance.selection(adults, children, fitnessHandler, retainNo);
        for (int i = 0; i < retainNo; i++) {
            assertEquals(expResult.get(i), result.get(i));
        }
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
