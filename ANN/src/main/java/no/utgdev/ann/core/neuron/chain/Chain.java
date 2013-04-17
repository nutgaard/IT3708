/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.neuron.chain;

import no.utgdev.ann.core.neuron.Neuron;

/**
 *
 * @author Nicklas
 */
public class Chain {

    protected ChainLink[] items;

    public Chain(ChainLink... items) {
        this.items = items;
    }

    public double calculate(Neuron neuron, double chainValue) {
        for (ChainLink item : items) {
            chainValue = item.calculate(neuron, chainValue);
        }
        return chainValue;
    }
}
