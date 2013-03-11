/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.node.filter;

import no.utgdev.ann.core.common.neuron.AbstractNeuron;

/**
 *
 * @author Nicklas
 */
public interface NodeConnectionFilter {
    public boolean connection(AbstractNeuron from, AbstractNeuron to);
}
