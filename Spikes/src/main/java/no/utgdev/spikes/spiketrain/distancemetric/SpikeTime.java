/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes.spiketrain.distancemetric;

import java.util.List;
import no.utgdev.spikes.spiketrain.TimingSpikeTrain;

/**
 *
 * @author Nicklas
 */
public class SpikeTime implements DistanceMetric {

    public double distanceMetric(TimingSpikeTrain train1, TimingSpikeTrain train2) {
        int[] t1 = train1.getTimings();
        int[] t2 = train2.getTimings();
        int min = Math.min(t1.length, t2.length);//M
        double dist = 0.0;
        double p = 2.0;
        for (int i = 0; i < min; i++) {
            dist += Math.pow(Math.abs(t1[i] - t2[i]), p);
        }
        //Spike penalties
        if (min == 0) {
            min++;
        }
        if (t1.length != t2.length) {
            int diff = Math.abs(t1.length - t2.length);
            double penalty = (diff * train1.getRaw().length) / (2 * min);
            dist += penalty;
        }
        return Math.pow(dist, 1 / p) / min;
    }
}
