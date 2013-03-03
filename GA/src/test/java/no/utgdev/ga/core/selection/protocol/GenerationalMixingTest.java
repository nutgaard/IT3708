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
import no.utgdev.ga.core.population.binary.BinaryPhenoType;
import no.utgdev.ga.core.utils.TestUtils;

/**
 *
 * @author Nicklas
 */
public class GenerationalMixingTest extends TestCase {

    public GenerationalMixingTest(String testName) {
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
     * Test of selection method, of class GenerationalMixing.
     */
    public void testSelection() {
        System.out.println("selection");
        Population adults = TestUtils.createPopulation(new String[]{
                    "10010000",
                    "01000000"
                });
        Population children = TestUtils.createPopulation(new String[]{
                    "01000100",
                    "00010101"
                });
        GALoop ga = new GALoop(null);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        int retainNo = 2;
        GenerationalMixing instance = new GenerationalMixing(ga);
        Population<BinaryPhenoType> expResult = TestUtils.createPopulation(new String[]{
                    "00010101",
                    "10010000"
                });
        Population<BinaryPhenoType> result = instance.selection(adults, children, fitnessHandler, retainNo);
        for (int i = 0; i < retainNo; i++) {
            boolean[] exp = expResult.get(i).getGenoType().getVector();
            boolean[] res = result.get(i).getGenoType().getVector();
            for (int j = 0; j < 8; j++) {
                assertEquals(exp[j], res[j]);
            }
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
