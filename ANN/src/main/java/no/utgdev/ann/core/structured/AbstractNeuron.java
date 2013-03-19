/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.structured;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nicklas
 */
public abstract class AbstractNeuron {
    protected double output;
    private double newOutput;
    private double staticInput = 0.0;
    protected final List<Synapse> input = new LinkedList<Synapse>();

    public AbstractNeuron() {
    }
    
    public void addInput(Synapse as) {
        this.input.add(as);
    }
    public void setStaticInput(double input) {
        this.staticInput = input;
    }
    public abstract double activationFunction(double input);
    
    public double getOutput() {
        return this.output;
    }
    public void update() {
        double sum = 0.0;
        sum += staticInput;
        for (Synapse as : input) {
            sum += as.getWeight() * as.getFrom().getOutput();
        }
        newOutput = activationFunction(sum);
    }
    public void sync() {
        this.output = newOutput;
        newOutput = 0.0;
    }
    public List<Synapse> getInputSynapses() {
        return this.input;
    }
}
