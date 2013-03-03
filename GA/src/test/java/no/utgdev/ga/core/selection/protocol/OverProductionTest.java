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
public class OverProductionTest extends TestCase {
    
    public OverProductionTest(String testName) {
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
     * Test of selection method, of class OverProduction.
     */
    public void testSelection() {
        System.out.println("selection");
        Population adults = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        Population children = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        GALoop ga = new GALoop(null);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        int retainNo = 3;
        OverProduction instance = new OverProduction(ga);
        Population<BinaryPhenoType> expResult = TestUtils.createPopulation(new String[]{
            "11111111",
            "11111100",
            "11110000"
        });
        Population<BinaryPhenoType> result = instance.selection(adults, children, fitnessHandler, retainNo);
        for (int i = 0; i < retainNo; i++) {
            boolean[] exp = expResult.get(i).getGenoType().getVector();
            boolean[] res = result.get(i).getGenoType().getVector();
            assertEquals(exp.length, res.length);
            for (int j = 0; j < exp.length; j++){
                assertEquals(exp[j], res[j]);
            }
        }
    }

    /**
     * Test of generatationRatio method, of class OverProduction.
     */
    public void testGeneratationRatio() {
        System.out.println("generatationRatio");
        GALoop ga = new GALoop(null);
        OverProduction instance = new OverProduction(ga);
        double expResult = 1.5;
        double result = instance.generatationRatio();
        assertEquals(expResult, result, 0.0);
    }
}
