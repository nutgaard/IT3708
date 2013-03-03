/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes;

import java.io.File;
import no.utgdev.ga.core.GALoop;
import no.utgdev.spikes.plotting.SpikeTrainPlotter;
import no.utgdev.spikes.spiketrain.RawSpikeTrain;
import no.utgdev.spikes.spiketrain.SpikeTrainFromFile;
import no.utgdev.spikes.spiketrain.SpikeTrainFromParameters;
import no.utgdev.spikes.spiketrain.TimingSpikeTrain;
import no.utgdev.spikes.spiketrain.distancemetric.DistanceMetric;
import no.utgdev.spikes.spiketrain.distancemetric.SpikeInterval;
import no.utgdev.spikes.spiketrain.distancemetric.SpikeTime;
import no.utgdev.spikes.spiketrain.distancemetric.Waveform;
import org.javatuples.Sextet;

/**
 *
 * @author Nicklas
 */
public class Plot {

    public static void main(String[] args) {
        GALoop ga = new GALoop(null);
        RawSpikeTrain fromFile = new SpikeTrainFromFile(ga).generate(new File("./data/izzy-train4.dat"));
        RawSpikeTrain fromParams = new SpikeTrainFromParameters(ga).generate(
                new Sextet<Integer, Double, Double, Double, Double, Double>(
                //                1000, 0.016, 0.1096, -44.875, 3.03, 0.04100625));
                //                  1000, 0.0015509502671430587, 0.2665931974335542, -40.412793820916114, 4.121273017358628, 0.04151086397539399));
//                1000, 0.03187021260842225, 0.08999488989356666, -47.44683863010576, 5.625769527175357, 0.04723993733396751));
//                1000, 0.0033336862764943792, 0.015571864757487487, -44.85168599283867, 7.847300459047454, 0.06695561914562975));
//                1000, 0.004476355757786127, 0.012654024857400928, -30.0, 10.0, 0.07574172929196699));
                1000, 0.0041205428089576505, 0.010841483776849454, -41.61981223226516, 8.738950405427698, 0.0699290384445009));
        SpikeTrainPlotter plotter = new SpikeTrainPlotter(fromFile, fromParams);
        TimingSpikeTrain timing = new TimingSpikeTrain(ga, fromFile);
        TimingSpikeTrain timing2 = new TimingSpikeTrain(ga, fromParams);
        for (int time = 0; time < Math.min(timing.getTimings().length, timing2.getTimings().length); time++) {
            System.out.println(timing.getTimings()[time] + " " + timing2.getTimings()[time]);
        }
        DistanceMetric dm = new SpikeTime();
        double distance = dm.distanceMetric(timing, timing2);
        System.out.println("Dist: " + distance);
    }
}
