/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.mechanism;

import java.util.Map;
import junit.framework.TestCase;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.BinaryFitnessHandler;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.selection.range.Range;
import no.utgdev.ga.core.selection.range.RangeMap;
import no.utgdev.ga.core.utils.TestUtils;

/**
 *
 * @author Nicklas
 */
public class SigmaScalingMechanismTest extends TestCase {
    
    public SigmaScalingMechanismTest(String testName) {
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
     * Test of createRangeMap method, of class SigmaScalingMechanism.
     */
    public void testCreateRangeMap() {
        System.out.println("createRangeMap");
         Population<PhenoType> population = TestUtils.createPopulation(new String[]{
            "11111111",
            "11110000"
        });
         GALoop ga = new GALoop(null);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        SigmaScalingMechanism instance = new SigmaScalingMechanism(ga);
        instance.createRangeMap(population, fitnessHandler);
        double[] expResult = new double[]{
            0.75,
            0.25
        };
        RangeMap<PhenoType> map = instance.getRangeMap();
        int c = 0;
        for (Map.Entry<Range, PhenoType> r : map.entrySet()) {
            assertEquals(expResult[c++], r.getKey().getEnd()-r.getKey().getStart(), 0.000001);
        }
    }
}
