/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.mechanism;

import java.text.DecimalFormat;
import java.util.Map.Entry;
import junit.framework.TestCase;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.BinaryFitnessHandler;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.selection.range.Range;
import no.utgdev.ga.core.selection.range.RangeMap;
import no.utgdev.ga.core.utils.TestUtils;
import static org.mockito.Mockito.*;

/**
 *
 * @author Nicklas
 */
public class FitnessProportionateMechanismTest extends TestCase {
    
    public FitnessProportionateMechanismTest(String testName) {
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
     * Test of createRangeMap method, of class FitnessProportionateMechanism.
     */
    public void testCreateRangeMap() {
        System.out.println("createRangeMap");
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
        FitnessProportionateMechanism instance = new FitnessProportionateMechanism(ga);
        instance.createRangeMap(population, fitnessHandler);
        RangeMap<PhenoType> map = instance.getRangeMap();
        double[] expResult = new double[]{
            0.02, 0.04,
            0.05, 0.07,
            0.09, 0.11,
            0.13, 0.15,
            0.16, 0.18
        };
        int c = 0;
        for (Entry<Range, PhenoType> r : map.entrySet()) {
            System.out.println(r);
            assertEquals(expResult[c++], round(r.getKey().getEnd()-r.getKey().getStart()));
//            assertEquals(expResult[c++], r.getKey().getEnd()-r.getKey().getStart(), 0.00000001);
        }
        
    }
    private static double round(double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(d).replace(",", "."));
    }
}
