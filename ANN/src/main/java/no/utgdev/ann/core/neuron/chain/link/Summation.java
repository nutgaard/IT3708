/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.neuron.chain.link;

import no.utgdev.ann.core.Synapse;
import no.utgdev.ann.core.neuron.Neuron;
import no.utgdev.ann.core.neuron.chain.ChainLink;

/**
 *
 * @author Nicklas
 */
public class Summation implements ChainLink {

    public double calculate(Neuron neuron, double value) {
        double sum = neuron.getStaticInput();
        for (Synapse synapse : neuron.getInputSynapses()) {
            sum += synapse.getWeight() * synapse.getFrom().getOutputValue();
        }
        return sum;
    }
    
}
