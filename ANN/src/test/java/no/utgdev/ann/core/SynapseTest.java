/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core;

import java.util.Arrays;
import no.utgdev.ann.core.neuron.Neuron;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Nicklas
 */
@RunWith(Parameterized.class)
public class SynapseTest {
    private final Synapse synapse;
    private final Neuron from, to;
    private final double weight, expWeight;
    
    @Parameterized.Parameters(name="{index}: {0}")
    public static Iterable<Object[]> params() {
        return Arrays.asList(new Object[][]{
            {new Synapse(new Neuron(null), new Neuron(null)), 1.0},
            {new Synapse(new Neuron(null), new Neuron(null), 2.0), 2.0}
        });
    }
    
    
    public SynapseTest(Synapse synapse, double expWeight) {
        this.synapse = synapse;
        this.from = synapse.from;
        this.to = synapse.to;
        this.weight = synapse.weight;
        this.expWeight = expWeight;
    }
    @Before
    public void reset() {
        synapse.weight = expWeight;
    }

    @Test
    public void testGetFrom() {
        assertEquals(from, synapse.getFrom());
    }

    @Test
    public void testGetTo() {
        assertEquals(to, synapse.getTo());
    }

    @Test
    public void testGetWeight() {
        assertEquals(expWeight, synapse.getWeight(), TestConstants.delta);
    }

    @Test
    public void testSetWeight() {
        assertEquals(expWeight, synapse.getWeight(), TestConstants.delta);
        double newExpectedWeight = 1337.0;
        synapse.setWeight(newExpectedWeight);
        assertEquals(newExpectedWeight, synapse.getWeight(), TestConstants.delta);        
    }
}
