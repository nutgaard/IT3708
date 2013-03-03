/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes;

import no.utgdev.ga.core.selection.mechanism.FitnessProportionateMechanism;
import no.utgdev.ga.core.selection.mechanism.RankMechanism;
import no.utgdev.ga.core.selection.mechanism.SigmaScalingMechanism;
import no.utgdev.ga.core.selection.mechanism.TournamentSelectionMechanism;
import no.utgdev.ga.core.selection.protocol.FullGenerationReplacement;
import no.utgdev.ga.core.selection.protocol.GenerationalMixing;
import no.utgdev.ga.core.selection.protocol.OverProduction;
import no.utgdev.spikes.spiketrain.distancemetric.SpikeInterval;
import no.utgdev.spikes.spiketrain.distancemetric.SpikeTime;
import no.utgdev.spikes.spiketrain.distancemetric.Waveform;

/**
 *
 * @author Nicklas
 */
public class Automagic {

    public static void main(String[] args) {
        String[] izzy = {"./data/izzy-train1.dat",
            "./data/izzy-train2.dat",
            "./data/izzy-train3.dat",
            "./data/izzy-train4.dat",};
        String[] protocol = {//FullGenerationReplacement.class.getName(),
            GenerationalMixing.class.getName(), //OverProduction.class.getName()};
        };
        String[] mechanism = {//FitnessProportionateMechanism.class.getName(),
            SigmaScalingMechanism.class.getName(),
            TournamentSelectionMechanism.class.getName(), //RankMechanism.class.getName()};
        };
        String[] sdm = {SpikeTime.class.getName(),
            SpikeInterval.class.getName(), //Waveform.class.getName()};
        };
        double lasttime;
        for (String i : izzy) {
            for (String p : protocol) {
                for (String m : mechanism) {
                    for (String s : sdm) {
                        for (double cr = 0.5; cr <= 1.0; cr += 0.1) {
                            for (double mr = 0; mr <= 0.5; mr += 0.1) {
                                for (int psize = 25; psize <= 200; psize += 25) {
                                    String[] input = {
                                        p,
                                        m,
                                        "20",
                                        "0.05",
                                        String.valueOf(cr),
                                        String.valueOf(mr),
                                        s,
                                        String.valueOf(psize),
                                        i};
                                    lasttime = System.currentTimeMillis();
                                    new App().main(input);
                                    System.out.println("TimeStep: " + (System.currentTimeMillis() - lasttime));
                                }
//                                    System.out.println("herp");
                            }
                        }
                        System.out.println("Complete with " + s);
                    }
                }
            }
        }
    }
}
