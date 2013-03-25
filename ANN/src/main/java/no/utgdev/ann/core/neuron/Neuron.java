/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.neuron;

import java.util.LinkedList;
import java.util.List;
import no.utgdev.ann.core.Synapse;
import no.utgdev.ann.core.neuron.chain.Chain;
import no.utgdev.ann.core.neuron.chain.link.CTRNNTransformation;
import no.utgdev.ann.core.neuron.chain.link.Summation;
import no.utgdev.ann.core.neuron.chain.link.activationfunction.IdentityActivationFunction;

/**
 *
 * @author Nicklas
 */
public class Neuron {

    protected double newOutputValue, outputValue, staticInput;
    protected final List<Synapse> inputSynapses;
    protected final Chain transformationChain;

    public Neuron(Chain transformationChain) {
        this.inputSynapses = new LinkedList<Synapse>();
        this.transformationChain = transformationChain;
        this.staticInput = 0.0;
    }

    public List<Synapse> getInputSynapses() {
        return this.inputSynapses;
    }

    public double getOutputValue() {
        return this.outputValue;
    }

    public void update() {
        this.newOutputValue = transformationChain.calculate(this, outputValue);
    }

    public void sync() {
        this.outputValue = newOutputValue;
    }
    public void setStaticInput(double input) {
        this.staticInput = input;
    }
    public double getStaticInput() {
        return this.staticInput;
    }
    public void addInputSynapse(Synapse synapse) {
        this.inputSynapses.add(synapse);
    }
}
