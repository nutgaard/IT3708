/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population;

import java.util.Iterator;
import junit.framework.TestCase;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.BinaryFitnessHandler;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.binary.BinaryPhenoType;
import no.utgdev.ga.core.utils.TestUtils;
import org.javatuples.Pair;
import static org.mockito.Mockito.*;

/**
 *
 * @author Nicklas
 */
public class PopulationTest extends TestCase {
    
    public PopulationTest(String testName) {
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
     * Test of get method, of class Population.
     */
    public void testGet() {
        System.out.println("get");
        int index = 0;
        String[] geno = TestUtils.createDefaultPopulation();
        Population<BinaryPhenoType> instance = TestUtils.createPopulation(geno);
        
        for (int i = 0; i < geno.length; i++){
            assertEquals(geno[i], TestUtils.booleanVectorToString(instance.get(i).getGenoType().getVector()));
        }
    }

    /**
     * Test of best method, of class Population.
     */
    public void testBest() {
        System.out.println("best");
        int nof = 0;
        String[] geno = TestUtils.createDefaultPopulation();
        GALoop ga = new GALoop(null);
        FitnessHandler fitnessHandler = new BinaryFitnessHandler(ga);
        Population<BinaryPhenoType> instance = TestUtils.createPopulation(geno);
        Population<BinaryPhenoType> bests = TestUtils.createPopulation(new String[]{
            "11111111",
            "11111100",
            "11110000",
            "11000000",
            "00000000"
        });      
        
        Pair<Population<BinaryPhenoType>, FitnessMap<BinaryPhenoType>> result = instance.best(1, fitnessHandler);
        booleanArrayEquals(bests.get(0).genoType.getVector(), result.getValue0().get(0).genoType.getVector());
        assertEquals(1.0, result.getValue1().get(result.getValue0().get(0)));
        
        result = instance.best(2, fitnessHandler);
        booleanArrayEquals(bests.get(0).genoType.getVector(), result.getValue0().get(0).genoType.getVector());
        booleanArrayEquals(bests.get(1).genoType.getVector(), result.getValue0().get(1).genoType.getVector());
        assertEquals(1.0, result.getValue1().get(result.getValue0().get(0)));
        assertEquals(0.75, result.getValue1().get(result.getValue0().get(1)));
        
        result = instance.best(3, fitnessHandler);
        booleanArrayEquals(bests.get(0).genoType.getVector(), result.getValue0().get(0).genoType.getVector());
        booleanArrayEquals(bests.get(1).genoType.getVector(), result.getValue0().get(1).genoType.getVector());
        booleanArrayEquals(bests.get(2).genoType.getVector(), result.getValue0().get(2).genoType.getVector());
        assertEquals(1.0, result.getValue1().get(result.getValue0().get(0)));
        assertEquals(0.75, result.getValue1().get(result.getValue0().get(1)));
        assertEquals(0.5, result.getValue1().get(result.getValue0().get(2)));
        
    }
    private void booleanArrayEquals(boolean[] exp, boolean[] res) {
        assertEquals(exp.length, res.length);
        for (int i = 0; i < exp.length; i++){
            assertEquals(exp[i], res[i]);
        }
    }

    /**
     * Test of merge method, of class Population.
     */
    public void testMerge() {
        System.out.println("merge");
        Population instance = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        Population instance2 = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        Population result = instance.merge(instance2);
        assertEquals(instance.size()*2, result.size());
    }

    /**
     * Test of subset method, of class Population.
     */
    public void testSubset() {
        System.out.println("subset");
        int size = 2;
        Population instance = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        Population result = instance.subset(size);
        assertEquals(size, result.size());
    }


    /**
     * Test of size method, of class Population.
     */
    public void testSize() {
        System.out.println("size");
        Population instance = TestUtils.createPopulation(TestUtils.createDefaultPopulation());
        int expResult = 5;
        int result = instance.size();
        assertEquals(expResult, result);
    }
}
