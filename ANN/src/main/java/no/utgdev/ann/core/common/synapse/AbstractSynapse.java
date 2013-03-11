/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.common.synapse;

import no.utgdev.ann.core.common.neuron.AbstractNeuron;

/**
 *
 * @author Nicklas
 */
public abstract class AbstractSynapse {

    protected final AbstractNeuron from, to;
    protected double weight;

    public AbstractSynapse(AbstractNeuron from, AbstractNeuron to) {
        this(from, to, 1.0);
    }

    public AbstractSynapse(AbstractNeuron from, AbstractNeuron to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        
        addToNeurons();
    }
    private void addToNeurons() {
        from.addOutput(this);
        to.addInput(this);
    }
    public AbstractNeuron getFrom() {
        return from;
    }

    public AbstractNeuron getTo() {
        return to;
    }

    public double getWeight() {
        return weight;
    }
}
