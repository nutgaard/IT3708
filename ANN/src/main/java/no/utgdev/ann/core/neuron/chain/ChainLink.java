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
public interface ChainLink {
    public double calculate(Neuron neuron, double value);
}
