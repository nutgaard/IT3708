/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.structured;

import no.utgdev.ann.core.structured.AbstractNeuron;
import java.util.List;

/**
 *
 * @author Nicklas
 */
public class NeuralLayer {

    public enum Type {

        INPUT, HIDDEN, OUTPUT;
    }
    protected final List<? extends AbstractNeuron> neurons;

    public NeuralLayer(List<? extends AbstractNeuron> neurons) {
        this.neurons = neurons;
    }

    public void update() {
        for (AbstractNeuron an : neurons) {
            an.update();
        }
    }

    public void sync() {
        for (AbstractNeuron an : neurons) {
            an.sync();
        }
    }

    public List<? extends AbstractNeuron> getNeurons() {
        return this.neurons;
    }

    public int size() {
        return this.neurons.size();
    }
}
