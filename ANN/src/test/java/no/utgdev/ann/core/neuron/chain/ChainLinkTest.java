/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.neuron.chain;

import no.utgdev.ann.core.neuron.Neuron;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nicklas
 */
public class ChainLinkTest {
    
    public ChainLinkTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCalculate() {
    }

    public class ChainLinkImpl implements ChainLink {

        public double calculate(Neuron neuron, double value) {
            return 0.0;
        }
    }
}
