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
public class SpikeInterval implements DistanceMetric {

    public double distanceMetric(TimingSpikeTrain train1, TimingSpikeTrain train2) {
        int[] t1 = train1.getTimings();
        int[] t2 = train2.getTimings();
        int min = Math.min(t1.length, t2.length);
        double dist = 0.0;
        double p = 2.0;
        for (int i = 1; i < min; i++) {
            dist += Math.pow(Math.abs((t1[i] - t1[i - 1]) - (t2[i] - t2[i - 1])), p);
        }
        if (min == 0) {
            min++;
        }
        //Spike penalties
        if (t1.length != t2.length) {
            int diff = Math.abs(t1.length-t2.length);
            double penalty = (diff * train1.getRaw().length) / (2 * min);
            dist += penalty;
        }
        if (min == 1) {
            min++;
        }
        return Math.pow(dist, 1/p)/(min-1);
    }
}
