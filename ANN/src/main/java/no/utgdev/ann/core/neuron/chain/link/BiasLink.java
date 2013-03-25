/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.neuron.chain.link;

import no.utgdev.ann.core.neuron.Neuron;
import no.utgdev.ann.core.neuron.chain.ChainLink;

/**
 *
 * @author Nicklas
 */
public class BiasLink implements ChainLink{
    private final double bias;

    public BiasLink() {
        this(1.0);
    }

    public BiasLink(double bias) {
        this.bias = bias;
    }
    

    public double calculate(Neuron neuron, double value) {
        return bias;
    }
    
}
