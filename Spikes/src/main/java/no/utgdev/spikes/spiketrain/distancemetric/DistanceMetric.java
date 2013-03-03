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
public interface DistanceMetric {
    public double distanceMetric(TimingSpikeTrain train1, TimingSpikeTrain train2);
}
