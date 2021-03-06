/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core;

import java.util.LinkedList;
import java.util.List;
import no.utgdev.ann.core.neuron.Neuron;

/**
 *
 * @author Nicklas
 */
public class StructuredANN implements NeuralNet {

    private NeuralLayer inputLayer;
    private List<NeuralLayer> hiddenLayers;
    private NeuralLayer outputLayer;

    public StructuredANN() {
        this.hiddenLayers = new LinkedList<NeuralLayer>();
    }

    public void addLayer(NeuralLayer.Type type, NeuralLayer layer) {
        switch (type) {
            case INPUT:
                inputLayer = layer;
                break;
            case HIDDEN:
                hiddenLayers.add(layer);
                break;
            case OUTPUT:
                outputLayer = layer;
                break;
            default:
                throw new RuntimeException("Could not recognize layer type <" + type + ">");
        }
    }

    public double[] update(double[] input) {
        if (inputLayer.size() != input.length) {
            throw new IllegalArgumentException("Unmatched input, was <"+input.length+"> expected <"+inputLayer.size()+">");
        }
        //Setting up input and recalculating inputlayer
        int inputInd = 0;
        for (Neuron an : inputLayer.getNeurons()){
            an.setStaticInput(input[inputInd++]);
            an.update();
            an.sync();
        }
        //Foreach hidden layer progagate changes
        NeuralLayer layer;
        for (int i = 0; i < hiddenLayers.size(); i++) {
            layer = hiddenLayers.get(i);
            //Calculate new values for every node
            layer.update();
            //Switch new values for old ones, in order to get this layer ready for next layer
            layer.sync();
        }
        //Recalculate output layer
        outputLayer.update();
        outputLayer.sync();
        //Assemble outputvector
        double[] output = new double[outputLayer.size()];
        int outputInd = 0;
        for (Neuron an : outputLayer.getNeurons()) {
            output[outputInd++] = an.getOutputValue();
        }
        return output;
    }
    public NeuralLayer[] getLayers(){
        NeuralLayer[] l = new NeuralLayer[hiddenLayers.size()+2];
        l[0] = inputLayer;
        l[l.length-1] = outputLayer;
        int i = 1;
        for (NeuralLayer nl : hiddenLayers) {
            l[i++] = nl;
        }
        return l;
    }
}
