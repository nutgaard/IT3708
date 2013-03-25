/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core;

import java.util.List;
import no.utgdev.ann.core.neuron.Neuron;
import no.utgdev.ann.core.neuron.Neuron;

/**
 *
 * @author Nicklas
 */
public class NeuralLayer {

    public enum Type {

        INPUT, HIDDEN, OUTPUT;
    }
    protected final List<Neuron> neurons;

    public NeuralLayer(List<Neuron> neurons) {
        this.neurons = neurons;
    }

    public void update() {
        for (Neuron an : neurons) {
            an.update();
        }
    }

    public void sync() {
        for (Neuron an : neurons) {
            an.sync();
        }
    }

    public List<Neuron> getNeurons() {
        return this.neurons;
    }

    public int size() {
        return this.neurons.size();
    }
}
