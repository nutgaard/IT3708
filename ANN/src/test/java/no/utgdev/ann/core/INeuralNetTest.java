/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core;

import java.util.ArrayList;
import java.util.Collection;
import junit.framework.TestCase;
import no.utgdev.ann.core.structured.StructuredANN;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Nicklas
 */
@RunWith(Parameterized.class)
public class INeuralNetTest extends TestCase {

    private INeuralNet neuralnet;

    public INeuralNetTest(INeuralNet neuralnet) {
        super(neuralnet.getClass().getSimpleName());
        System.out.println("Running: "+neuralnet);
        this.neuralnet = neuralnet;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        Collection<Object[]> implCases = new ArrayList<Object[]>();
        implCases.add(new Object[]{new INeuralNetImpl()});
        implCases.add(new Object[]{new StructuredANN()});
        return implCases;
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
     * Test of update method, of class INeuralNet.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        double[] input = null;
        INeuralNet instance = new INeuralNetImpl();
        double[] expResult = null;
        double[] result = instance.update(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public static class INeuralNetImpl implements INeuralNet {

        public double[] update(double[] input) {
            return null;
        }
    }
}
