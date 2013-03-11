/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.node;

import no.utgdev.ann.core.common.neuron.AbstractNeuron;
import no.utgdev.ann.core.common.neuron.BaseNeuron;
import no.utgdev.ann.core.node.NodeBasedANN.LayerType;
import no.utgdev.ann.core.node.filter.FullConnection;
import no.utgdev.ann.core.node.filter.NodeConnectionFilter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Nicklas
 */
@RunWith(value = Parameterized.class)
public class NodeBasedANNTest {
    
    public NodeBasedANNTest() {
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
    /**
     * Test complete build of node based ANN
     */
    @Test
    public void testCompleteBuild() {
        NodeBasedANN ann = new NodeBasedANN();
        
        Layer input = new Layer();
        input.addNeuron(new BaseNeuron());
        input.addNeuron(new BaseNeuron());
        input.addNeuron(new BaseNeuron());
        input.addNeuron(new BaseNeuron());
        input.addNeuron(new BaseNeuron());
        
        Layer hidden = new Layer();
        hidden.addNeuron(new BaseNeuron());
        hidden.addNeuron(new BaseNeuron());
        
        Layer output = new Layer();
        output.addNeuron(new BaseNeuron());
        output.addNeuron(new BaseNeuron());
        
        Layer bias = new Layer();
        bias.addNeuron(new BaseNeuron());
        
        ann.addLayer(input, LayerType.INPUT);
        ann.addLayer(hidden, LayerType.HIDDEN);
        ann.addLayer(output, LayerType.OUTPUT);
        ann.addLayer(bias, LayerType.STATIC);
        
        ann.addSynapses(new FullConnection(), LayerType.INPUT, LayerType.HIDDEN);
        ann.addSynapses(new FullConnection(), LayerType.HIDDEN, LayerType.HIDDEN);
        ann.addSynapses(new FullConnection(), LayerType.HIDDEN, LayerType.OUTPUT);
        ann.addSynapses(new FullConnection(), LayerType.OUTPUT, LayerType.OUTPUT);
        ann.addSynapses(new FullConnection(), LayerType.STATIC, LayerType.HIDDEN);
        ann.addSynapses(new FullConnection(), LayerType.STATIC, LayerType.OUTPUT);
        
        int synapseCount = 0;
        for (AbstractNeuron an : input.getNeurons()) {
            synapseCount += an.getOutput().size();
        }
        System.out.println("After input: "+synapseCount);
        for (AbstractNeuron an : hidden.getNeurons()) {
            synapseCount += an.getOutput().size();
        }
        System.out.println("After hidden: "+synapseCount);
        for (AbstractNeuron an : output.getNeurons()) {
            synapseCount += an.getOutput().size();
        }
        System.out.println("After output: "+synapseCount);
        for (AbstractNeuron an : bias.getNeurons()) {
            synapseCount += an.getOutput().size();
        }
        System.out.println("After bias: "+synapseCount);
        System.out.println(synapseCount);
        assertEquals(26, synapseCount);
    }

    /**
     * Test of addLayer method, of class NodeBasedANN.
     */
    @Test
    public void testAddInputLayer() {
        System.out.println("addLayer");
        Layer layer = new Layer();
        LayerType type = LayerType.INPUT;
        NodeBasedANN instance = new NodeBasedANN();
        instance.addLayer(layer, type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSynapses method, of class NodeBasedANN.
     */
    @Test
    public void testAddSynapses_3args_1() {
        System.out.println("addSynapses");
        NodeConnectionFilter filter = null;
        Layer from = null;
        Layer to = null;
        NodeBasedANN instance = new NodeBasedANN();
        instance.addSynapses(filter, from, to);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSynapses method, of class NodeBasedANN.
     */
    @Test
    public void testAddSynapses_3args_2() {
        System.out.println("addSynapses");
        NodeConnectionFilter filter = null;
        LayerType fromType = null;
        LayerType toType = null;
        NodeBasedANN instance = new NodeBasedANN();
        instance.addSynapses(filter, fromType, toType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

