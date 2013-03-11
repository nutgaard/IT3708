/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.common.neuron;

import java.util.LinkedList;
import java.util.List;
import no.utgdev.ann.core.common.synapse.AbstractSynapse;

/**
 *
 * @author Nicklas
 */
public abstract class AbstractNeuron {
    private final List<AbstractSynapse> input = new LinkedList<AbstractSynapse>();
    private final List<AbstractSynapse> output = new LinkedList<AbstractSynapse>();
    
    public void addInput(AbstractSynapse as) {
        this.input.add(as);
    }
    public void addOutput(AbstractSynapse as) {
        this.output.add(as);
    }
    public List<AbstractSynapse> getOutput() {
        return this.output;
    }
    public List<AbstractSynapse> getInput() {
        return this.input;
    }
}
