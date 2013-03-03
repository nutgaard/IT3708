/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.mechanism;

import junit.framework.TestCase;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;

/**
 *
 * @author Nicklas
 */
public class TournamentSelectionMechanismTest extends TestCase {
    
    public TournamentSelectionMechanismTest(String testName) {
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
     * Test of getPhenoType method, of class TournamentSelectionMechanism.
     */
    public void testGetPhenoType() {
        System.out.println("getPhenoType");
        Population p = null;
        FitnessHandler fitnessHandler = null;
        GALoop ga = new GALoop(null);
        TournamentSelectionMechanism instance = new TournamentSelectionMechanism(ga);
        PhenoType expResult = null;
//        PhenoType result = instance.getPhenoType(p, fitnessHandler);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of selection method, of class TournamentSelectionMechanism.
     */
    public void testSelection() {
        System.out.println("selection");
        Population p = null;
        FitnessHandler fitnessHandler = null;
        int retain = 0;
        GALoop ga = new GALoop(null);
        TournamentSelectionMechanism instance = new TournamentSelectionMechanism(ga);
        Population expResult = null;
//        Population result = instance.selection(p, fitnessHandler, retain);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
