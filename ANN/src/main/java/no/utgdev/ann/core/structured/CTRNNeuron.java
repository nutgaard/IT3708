/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.structured;

/**
 *
 * @author Nicklas
 */
public class CTRNNeuron extends AbstractNeuron {
    private double gain, invTau;
    private double y = 0.0;

    public CTRNNeuron() {
        this(1.0, 1.0);
    }
    
    public CTRNNeuron(double gain, double tau) {
        this.gain = gain;
        this.invTau = 1.0/tau;
    }

    @Override
    public double activationFunction(double s) {
        y += invTau*(-y+s);
        return 1/(1+Math.exp(-gain*y));
    }    
}
