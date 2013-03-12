/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.structured;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nicklas
 */
public class TestIt {
    public static void main(String[] args) {
        List<AbstractNeuron> inputNeurons = new LinkedList<AbstractNeuron>();
        inputNeurons.add(new CTRNNeuron());
        inputNeurons.add(new CTRNNeuron());
        inputNeurons.add(new CTRNNeuron());
        inputNeurons.add(new CTRNNeuron());
        inputNeurons.add(new CTRNNeuron());
        NeuralLayer inputLayer = new NeuralLayer(inputNeurons);
        
        List<AbstractNeuron> hiddenNeurons = new LinkedList<AbstractNeuron>();
        hiddenNeurons.add(new CTRNNeuron());
        hiddenNeurons.add(new CTRNNeuron());
        NeuralLayer hiddenLayer = new NeuralLayer(hiddenNeurons);
        
        List<AbstractNeuron> biasNeurons = new LinkedList<AbstractNeuron>();
        biasNeurons.add(new BiasNeuron());
        NeuralLayer biasLayer = new NeuralLayer(biasNeurons);
        
        List<AbstractNeuron> outputNeurons = new LinkedList<AbstractNeuron>();
        outputNeurons.add(new CTRNNeuron());
        outputNeurons.add(new CTRNNeuron());
        NeuralLayer outputLayer = new NeuralLayer(outputNeurons);
        
        addFullConnection(inputLayer, hiddenLayer);
        addFullConnection(hiddenLayer, hiddenLayer);
        addFullConnection(hiddenLayer, outputLayer);
        addFullConnection(outputLayer, outputLayer);
        
        addFullConnection(biasLayer, hiddenLayer);
        addFullConnection(biasLayer, outputLayer);
        
        StructuredANN ann = new StructuredANN();
        ann.addLayer(NeuralLayer.Type.INPUT, inputLayer);
        ann.addLayer(NeuralLayer.Type.HIDDEN, biasLayer);
        ann.addLayer(NeuralLayer.Type.HIDDEN, hiddenLayer);
        ann.addLayer(NeuralLayer.Type.OUTPUT, outputLayer);
        
        int gens = 10;
        double[] input = {1.0, 1.0, 1.0, 1.0, 1.0};
        double[][] output = new double[gens][2];
        for (int i = 0; i < gens; i++) {
            output[i] = ann.update(input);
            System.out.println(Arrays.toString(output[i]));
        }
    }
    
    
    private static void addFullConnection(NeuralLayer from, NeuralLayer to) {
        for (AbstractNeuron anFrom : from.getNeurons()) {
            for (AbstractNeuron anTo : to.getNeurons()) {
                Synapse s = new Synapse(anFrom, anTo);
                anTo.addInput(s);
            }
        }
    }
}
