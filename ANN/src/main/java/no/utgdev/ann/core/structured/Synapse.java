/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.structured;

import no.utgdev.ann.core.structured.AbstractNeuron;

/**
 *
 * @author Nicklas
 */
public class Synapse {
    protected final AbstractNeuron from, to;
    protected double weight;

    public Synapse(AbstractNeuron from, AbstractNeuron to) {
        this(from, to, 1.0);
    }

    public Synapse(AbstractNeuron from, AbstractNeuron to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
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

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    
}
