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
public class StepActivationFunction implements ChainLink {

    private final double threshold, lowValue, highValue;

    public StepActivationFunction() {
        this(0, 0, 1);
    }

    public StepActivationFunction(double threshold) {
        this(threshold, 0, 1);
    }

    public StepActivationFunction(double threshold, double lowValue, double highValue) {
        this.threshold = threshold;
        this.lowValue = lowValue;
        this.highValue = highValue;
    }

    public double calculate(Neuron neuron, double value) {
        return (value > threshold) ? highValue : lowValue;
    }
}
