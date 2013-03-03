/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.mechanism;

import junit.framework.TestCase;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.BinaryFitnessHandler;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.binary.BinaryPhenoType;
import no.utgdev.ga.core.selection.range.Range;
import no.utgdev.ga.core.selection.range.RangeMap;
import no.utgdev.ga.core.utils.TestUtils;
import static org.mockito.Mockito.*;
//import static org.powermock.api.mockito.PowerMockito.*;


/**
 *
 * @author Nicklas
 */
public class RangeBasedSelectionMechanismTest extends TestCase {

    public RangeBasedSelectionMechanismTest(String testName) {
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
     * Test of getPhenoType method, of class RangeBasedSelectionMechanism.
     */
    public void testGetPhenoType() {
        System.out.println("getPhenoType");
        Population<PhenoType> population = TestUtils.createPopulation(new String[]{
                    "11111111", "11111111",
                    "11111111", "11111111",
                    "11111111", "11111111",
                    "11111111", "11111111",
                    "11111111", "11111111"
                });
        FitnessMap<PhenoType> fitnessMap = mock(FitnessMap.class);
        FitnessHandler fitnessHandler = mock(BinaryFitnessHandler.class);
        when(fitnessMap.get(any(PhenoType.class))).thenReturn(1., 2., 3., 4., 5., 6., 7., 8., 9., 10.);
        when(fitnessHandler.getFitness(any(PhenoType.class), any(Population.class))).thenReturn(1., 2., 3., 4., 5., 6., 7., 8., 9., 10.);
        when(fitnessHandler.generateFitnessMap(any(Population.class))).thenReturn(fitnessMap);
        GALoop ga = new GALoop(null);
        RangeMap<PhenoType> rangemap = new RangeMap<PhenoType>();
        double start = 0.0, end = 0.0;
        for (PhenoType pt : population) {
            double f = fitnessMap.get(pt);
            end += f;
            rangemap.put(new Range(start, end), pt);
            start += f;
        }
        rangemap.normalize();
        RangeBasedSelectionMechanism instance = new RangeBasedSelectionMechanismImpl(ga, rangemap);
        
        double random = 0.5*rangemap.lastEntry().getKey().getEnd();
        PhenoType expResult = population.get(6);
        PhenoType result = rangemap.findEntry(random).getValue();
        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of selection method, of class RangeBasedSelectionMechanism.
     */
    public void testSelection() {
        System.out.println("selection");
        Population p = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        GALoop ga = new GALoop(null);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        int retain = 3;
        RangeBasedSelectionMechanism instance = new RangeBasedSelectionMechanismImpl(ga);
        Population<BinaryPhenoType> expResult = TestUtils.createPopulation(new String[]{
            "11111111",
            "11111100",
            "11110000"
        });
        Population<BinaryPhenoType> result = instance.selection(p, fitnessHandler, retain);
        for (int i = 0; i < retain; i++) {
            boolean[] exp = expResult.get(i).getGenoType().getVector();
            boolean[] res = result.get(i).getGenoType().getVector();
            for (int j = 0; j < 8; j++) {
                assertEquals(exp[j], res[j]);
            }
        }
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getRangeMap method, of class RangeBasedSelectionMechanism.
     */
    public void testGetRangeMap() {
        System.out.println("getRangeMap");
        GALoop ga = new GALoop(null);
        RangeBasedSelectionMechanism instance = new RangeBasedSelectionMechanismImpl(ga);
        RangeMap expResult = null;
        RangeMap result = instance.getRangeMap();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    public class RangeBasedSelectionMechanismImpl extends RangeBasedSelectionMechanism {

        public RangeBasedSelectionMechanismImpl(GALoop ga) {
            super(ga);
        }

        public RangeBasedSelectionMechanismImpl(GALoop ga, RangeMap<PhenoType> r) {
            super(ga, r);
        }

        public void createRangeMap(Population<PhenoType> population, FitnessHandler fitnessHandler) {
        }
    }
}
