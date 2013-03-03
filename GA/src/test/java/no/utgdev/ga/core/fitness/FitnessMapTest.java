/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.fitness;

import junit.framework.TestCase;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.binary.BinaryGenoType;
import no.utgdev.ga.core.population.binary.BinaryPhenoType;
import no.utgdev.ga.core.utils.TestUtils;

/**
 *
 * @author Nicklas
 */
public class FitnessMapTest extends TestCase {

    public FitnessMapTest(String testName) {
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
     * Test of put method, of class FitnessMap.
     */
    public void testPut() {
        System.out.println("put");
        PhenoType key = new BinaryPhenoType(new BinaryGenoType(TestUtils.stringToBooleanVector("11111111")));
        Double value = 1.0;
        FitnessMap instance = new FitnessMap();
        instance.put(key, value);
        assertEquals(instance.get(key), value);
    }

    /**
     * Test of get method, of class FitnessMap.
     */
    public void testGet() {
        System.out.println("get");
        PhenoType[] key = new PhenoType[]{
            new BinaryPhenoType(new BinaryGenoType(TestUtils.stringToBooleanVector("11111111"))),
            new BinaryPhenoType(new BinaryGenoType(TestUtils.stringToBooleanVector("11111111"))),
            new BinaryPhenoType(new BinaryGenoType(TestUtils.stringToBooleanVector("11110011"))),
            new BinaryPhenoType(new BinaryGenoType(TestUtils.stringToBooleanVector("11110011"))),
            new BinaryPhenoType(new BinaryGenoType(TestUtils.stringToBooleanVector("00000000")))
        };
        FitnessMap instance = new FitnessMap();
        Double[] expResult = new Double[]{1.0, 1.0, 0.75, 0.75, 0.0};
        for (int i = 0; i < key.length; i++){
            instance.put(key[i], expResult[i]);
        }
        for (int i = 0; i < key.length; i++){
            assertEquals(expResult[i], instance.get(key[i]));
        }
    }
}
