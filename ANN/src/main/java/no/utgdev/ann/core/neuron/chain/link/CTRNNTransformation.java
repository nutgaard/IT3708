/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.neuron.chain.link;

import no.utgdev.ann.core.neuron.Neuron;
import no.utgdev.ann.core.neuron.chain.ChainLink;
import no.utgdev.ann.core.neuron.chain.link.activationfunction.LogSigmoidActiviationFunction;

/**
 *
 * @author Nicklas
 */
public class CTRNNTransformation extends LogSigmoidActiviationFunction {

    private final double gain, inverseTau;
    private double currentValue;

    public CTRNNTransformation(double gain, double tau) {
        super(gain);
        this.gain = gain;
        this.inverseTau = 1.0/tau;
        this.currentValue = 0.0;
    }

    public CTRNNTransformation() {
        this(1.0, 1.0);
    }

    public double calculate(Neuron neuron, double value) {
        this.currentValue += inverseTau*(-currentValue+value);
        return super.calculate(neuron, currentValue);
    }
    
}
