/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.neuron.chain.link.activationfunction;

import no.utgdev.ann.core.neuron.Neuron;
import no.utgdev.ann.core.neuron.chain.ChainLink;

/**
 *
 * @author Nicklas
 */
public class LogSigmoidActiviationFunction implements ChainLink {
    private final double beta;

    public LogSigmoidActiviationFunction() {
        this.beta = 1.0;
    }
    public LogSigmoidActiviationFunction(double beta) {
        this.beta = beta;
    }

    public double calculate(Neuron neuron, double value) {
        return 1.0/(1+Math.exp(-beta*value));
    }
    
    
}
