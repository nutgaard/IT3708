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
public class IdentityActivationFunction implements ChainLink {

    public double calculate(Neuron neuron, double value) {
        return value;
    }
    
}
