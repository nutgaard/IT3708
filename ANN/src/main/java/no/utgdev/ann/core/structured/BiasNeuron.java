/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.structured;

/**
 *
 * @author Nicklas
 */
public class BiasNeuron extends AbstractNeuron {

    public BiasNeuron() {
        output = 1.0;
    }

    @Override
    public double activationFunction(double input) {
        return 1.0;
    }
}
