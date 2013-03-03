/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes.spiketrain.distancemetric;

import no.utgdev.spikes.spiketrain.TimingSpikeTrain;

/**
 *
 * @author Nicklas
 */
public class Waveform implements DistanceMetric {

    public double distanceMetric(TimingSpikeTrain train1, TimingSpikeTrain train2) {
        double dist = 0.0;
        double p = 2.0;
        double[] raw1 = train1.getRaw();
        double[] raw2 = train2.getRaw();
        int min = Math.min(raw1.length, raw2.length);
        for (int i = 0; i < min; i++) {
            double diff = raw1[i]-raw2[i];
            dist += Math.pow(Math.abs(diff), p);
        }
        return Math.pow(dist, 1/p)/min;
    }
}
