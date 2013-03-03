/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population.parser;

import java.util.Collections;
import java.util.List;
import junit.framework.TestCase;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.BinaryFitnessHandler;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.binary.BinaryPhenoType;
import no.utgdev.ga.core.utils.TestUtils;

/**
 *
 * @author Nicklas
 */
public class RankFinderTest extends TestCase {

    public RankFinderTest(String testName) {
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
     * Test of parse method, of class RankFinder.
     */
    public void testParse_Population_FitnessHandler() {
        System.out.println("parse");
        Population<PhenoType> p = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        GALoop ga = new GALoop(null);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        RankFinder instance = new RankFinder();
        List<PhenoType> result = instance.parse(p, fitnessHandler);
//        Collections.reverse(result);
        Population<BinaryPhenoType> expResult = TestUtils.createPopulation(new String[]{
                    "00000000",
                    "11000000",
                    "11110000",
                    "11111100",
                    "11111111"
                });
        for (int i = 0; i < p.size(); i++) {
            boolean[] exp = expResult.get(i).getGenoType().getVector();
            boolean[] res = ((BinaryPhenoType) result.get(i)).getGenoType().getVector();
            for (int j = 0; j < 8; j++) {
                assertEquals(exp[j], res[j]);
            }
        }
    }
}
