/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection;

import junit.framework.TestCase;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.BinaryFitnessHandler;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.binary.BinaryPhenoType;
import no.utgdev.ga.core.selection.mechanism.NullMechanism;
import no.utgdev.ga.core.selection.protocol.NullProtocol;
import no.utgdev.ga.core.utils.TestUtils;

/**
 *
 * @author Nicklas
 */
public class SelectionStrategyTest extends TestCase {

    public SelectionStrategyTest(String testName) {
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
     * Test of selection method, of class SelectionStrategy.
     */
    public void testSelection() {
        System.out.println("selection");
        Population adult = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        Population children = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        int retain = 6;
        GALoop ga = new GALoop(null);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        SelectionStrategy instance = new SelectionStrategy(new NullProtocol(ga), new NullMechanism(ga));
        String[] expResult = new String[]{
                    "11111111",
                    "11111111",
                    "11111100",
                    "11111100",
                    "11110000",
                    "11110000"
                };
        Population<BinaryPhenoType> result = instance.selection(adult, children, retain, fitnessHandler);
        assertEquals(result.size(), retain);
        for (int i = 0; i < retain; i++) {
            assertEquals(expResult[i], TestUtils.booleanVectorToString(result.get(i).getGenoType().getVector()));
        }
    }
}
