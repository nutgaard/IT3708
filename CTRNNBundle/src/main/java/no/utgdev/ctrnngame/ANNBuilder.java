package no.utgdev.ctrnngame;

import java.util.Arrays;
import no.utgdev.ann.core.structured.AbstractNeuron;
import no.utgdev.ann.core.structured.CTRNNeuron;
import no.utgdev.ann.core.structured.NeuralLayer;
import no.utgdev.ann.core.structured.BiasNeuron;
import no.utgdev.ann.core.structured.StructuredANN;
import no.utgdev.ann.core.structured.Synapse;

/**
 * Hello world!
 *
 */
public class ANNBuilder {
    public static StructuredANN build(double[] data) {
        /**
         * data - must be 34 long
         * data[0..4] == gains for hidden then output
         * data[4..8] == time constants for hidden then output
         * data[8..34] == weights
         *      data[8..18] == weights originating in inputlayer
         *      data[18..26] == weights originating in hiddenLayer
         *      data[26..30] == weights originating in outputlayer
         *      data[30..34] == weights originating in baislayer
         */
        AbstractNeuron[] hiddenNeurons = new AbstractNeuron[]{
            new CTRNNeuron(data[0], data[4]),
            new CTRNNeuron(data[1], data[5]),
        };
        AbstractNeuron[] outputNeurons = new AbstractNeuron[]{
            new CTRNNeuron(data[2], data[6]),
            new CTRNNeuron(data[3], data[7]),
        };
        AbstractNeuron[] inputNeurons = new AbstractNeuron[]{
            new CTRNNeuron(),
            new CTRNNeuron(),
            new CTRNNeuron(),
            new CTRNNeuron(),
            new CTRNNeuron()
        };
        AbstractNeuron[] biasNeurons = new AbstractNeuron[]{
            new BiasNeuron()
        };
        NeuralLayer inputLayer = new NeuralLayer(Arrays.asList(inputNeurons));
        NeuralLayer hiddenLayer = new NeuralLayer(Arrays.asList(hiddenNeurons));
        NeuralLayer outputLayer = new NeuralLayer(Arrays.asList(outputNeurons));
        NeuralLayer biasLayer = new NeuralLayer(Arrays.asList(biasNeurons));
        
        addFullConnection(inputLayer, hiddenLayer, Arrays.copyOfRange(data, 8, 18));
        addFullConnection(hiddenLayer, hiddenLayer, Arrays.copyOfRange(data, 18, 22));
        addFullConnection(hiddenLayer, outputLayer, Arrays.copyOfRange(data, 22, 26));
        addFullConnection(outputLayer, outputLayer, Arrays.copyOfRange(data, 26, 30));
        addFullConnection(biasLayer, hiddenLayer, Arrays.copyOfRange(data, 30, 32));
        addFullConnection(biasLayer, outputLayer, Arrays.copyOfRange(data, 32, 34));
        
        StructuredANN ann = new StructuredANN();
        ann.addLayer(NeuralLayer.Type.INPUT, inputLayer);
        ann.addLayer(NeuralLayer.Type.HIDDEN, hiddenLayer);
        ann.addLayer(NeuralLayer.Type.HIDDEN, biasLayer);
        ann.addLayer(NeuralLayer.Type.OUTPUT, outputLayer);
        
        return ann;
    }
    private static void addFullConnection(NeuralLayer from, NeuralLayer to, double[] weights) {
        int wCount = 0;
        for (AbstractNeuron anFrom : from.getNeurons()) {
            for (AbstractNeuron anTo : to.getNeurons()) {
                Synapse s = new Synapse(anFrom, anTo, weights[wCount++]);
                anTo.addInput(s);
            }
        }
    }
//    public static void main(String[] args) {
//        double[] data = {
//            1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
//            1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
//            1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
//            1.0, 1.0, 1.0, 1.0
//        };
//        StructuredANN ann = build(data);
//        double[] input = {1.0, 1.0, 1.0, 1.0, 1.0};
//        for (int i = 0; i < 10; i++) {
//            System.out.println(Arrays.toString(ann.update(input)));
//        }
//    }
}
