/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import no.utgdev.ann.core.common.neuron.AbstractNeuron;
import no.utgdev.ann.core.common.synapse.BaseSynapse;
import no.utgdev.ann.core.node.filter.NodeConnectionFilter;

/**
 *
 * @author Nicklas
 */
public class Layer {

    private final List<AbstractNeuron> neurons;

    public Layer() {
        this.neurons = new ArrayList<AbstractNeuron>();
    }

    public Layer(List<AbstractNeuron> neurons) {
        this.neurons = neurons;
    }

    public Layer(AbstractNeuron... neurons) {
        this.neurons = Arrays.asList(neurons);
    }

    public void addNeuron(AbstractNeuron neuron) {
        this.neurons.add(neuron);
    }

    public AbstractNeuron getNeuron(int ind) {
        return this.neurons.get(ind);
    }

    public AbstractNeuron[] getNeuronsAsArray() {
        AbstractNeuron[] lst = new AbstractNeuron[neurons.size()];
        lst = neurons.toArray(lst);
        return lst;
    }

    public List<AbstractNeuron> getNeurons() {
        return this.neurons;
    }

    public void addSynapses(NodeConnectionFilter filter, Layer layer) {
        AbstractNeuron[] from = this.getNeuronsAsArray();
        AbstractNeuron[] to = layer.getNeuronsAsArray();
        for (AbstractNeuron anFrom : from) {
            for (AbstractNeuron anTo : to) {
                if (filter.connection(anFrom, anTo)) {
                    new BaseSynapse(anFrom, anTo);
                }
            }
        }
    }

    public void addSynapese(NodeConnectionFilter filter, AbstractNeuron anFrom, AbstractNeuron... to) {
        for (AbstractNeuron anTo : to) {
            if (filter.connection(anFrom, anTo)) {
                new BaseSynapse(anFrom, anTo);
            }
        }
    }
}
