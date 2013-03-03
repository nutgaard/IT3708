/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.mechanism;

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
public class NullMechanismTest extends TestCase {

    public NullMechanismTest(String testName) {
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
     * Test of getPhenoType method, of class NullMechanism.
     */
    public void testGetPhenoType() {
        System.out.println("getPhenoType");
        Population p = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        GALoop ga = new GALoop(null);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        NullMechanism instance = new NullMechanism(ga);
        PhenoType expResult = null;
        PhenoType result = instance.getPhenoType(p, fitnessHandler);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of selection method, of class NullMechanism.
     */
    public void testSelection() {
        System.out.println("selection");
        Population p = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        GALoop ga = new GALoop(null);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        int retain = 0;
        NullMechanism instance = new NullMechanism(ga);
        Population expResult = null;
        Population result = instance.selection(p, fitnessHandler, retain);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
