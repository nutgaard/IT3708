/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core;

import no.utgdev.ann.core.neuron.Neuron;

/**
 *
 * @author Nicklas
 */
public class Synapse {
    protected final Neuron from, to;
    protected double weight;

    public Synapse(Neuron from, Neuron to) {
        this(from, to, 1.0);
    }

    public Synapse(Neuron from, Neuron to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Neuron getFrom() {
        return from;
    }

    public Neuron getTo() {
        return to;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
