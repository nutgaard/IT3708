/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes.spiketrain;

/**
 *
 * @author Nicklas
 */
public class RawSpikeTrain {
    protected double[] raw;
    public RawSpikeTrain(double[] raw) {
        this.raw = raw;
    }
    public RawSpikeTrain(RawSpikeTrain raw) {
        this.raw = raw.raw;
    }

    public double[] getRaw() {
        return raw;
    }
    
}
