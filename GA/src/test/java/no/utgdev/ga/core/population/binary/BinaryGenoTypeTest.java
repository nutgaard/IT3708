/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population.binary;

import junit.framework.TestCase;
import no.utgdev.ga.core.utils.TestUtils;

/**
 *
 * @author Nicklas
 */
public class BinaryGenoTypeTest extends TestCase {
    
    public BinaryGenoTypeTest(String testName) {
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
     * Test of develop method, of class BinaryGenoType.
     */
    public void testDevelop() {
        System.out.println("develop");
        BinaryGenoType instance = new BinaryGenoType(TestUtils.stringToBooleanVector("11111111"));
        BinaryPhenoType expResult = new BinaryPhenoType(instance);
        BinaryPhenoType result = instance.develop();
        assertEquals(instance.getVector(), result.getGenoType().getVector());
    }

    
    /**
     * Test of crossover method, of class BinaryGenoType.
     */
    public void testCrossover() {
        System.out.println("crossover");
        BinaryGenoType other = new BinaryGenoType(TestUtils.stringToBooleanVector("11111111"));
        BinaryGenoType instance = new BinaryGenoType(TestUtils.stringToBooleanVector("00000000"));
        BinaryGenoType result = instance.crossover(other);
        assertNotSame(instance.getVector(), result.getVector());
        assertNotSame(other.getVector(), result.getVector());
        
    }

    /**
     * Test of mutate method, of class BinaryGenoType.
     */
    public void testMutate() {
        System.out.println("mutate");
        BinaryGenoType instance = new BinaryGenoType(TestUtils.stringToBooleanVector("11111111"));
        BinaryGenoType result = instance.mutate();
        assertNotSame(instance.getVector(), result.getVector());
    }
}
