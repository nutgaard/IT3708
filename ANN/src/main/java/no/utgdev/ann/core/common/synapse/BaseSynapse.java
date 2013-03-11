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
public class BaseSynapse extends AbstractSynapse {

    public BaseSynapse(AbstractNeuron from, AbstractNeuron to) {
        super(from, to);
    }

    public BaseSynapse(AbstractNeuron from, AbstractNeuron to, double weight) {
        super(from, to, weight);
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
