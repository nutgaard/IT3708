/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class RerunOfBest {

    public static void main(String[] args) {
        final int runPerCase = 10, retainBestCount = 5;
        runForIzzy(1, runPerCase, retainBestCount);
        runForIzzy(2, runPerCase, retainBestCount);
        runForIzzy(3, runPerCase, retainBestCount);
        runForIzzy(4, runPerCase, retainBestCount);
    }

    private static void runForIzzy(int izzydata, int runs, int retain) {
        File dir = new File("./backup/izzy" + izzydata + "/wave/");
        File[] typeFiltered = dir.listFiles(new FilenameFilter() {
            Pattern p = Pattern.compile("(\\d{1,4})-izzy(\\d)-(\\d*?)gen-(\\d*?)pop-(\\d*?)tour-(\\d\\.\\d*)eps-(\\d\\.\\d*)cr-(\\d\\.\\d*)mr-(\\d*?)acc-(\\w*)-(\\w*)-(\\w*)\\.png");

            public boolean accept(File dir, String name) {
                return p.matcher(name).find();
            }
        });
        Case[] bestFound = retainBest(typeFiltered, retain);
        int caseId = 0;
        double lastTime;
        for (Case c : bestFound) {
            System.out.println("Izzydata " + izzydata + " case: " + caseId);
            File newDir = new File(dir.getParentFile(), "configswave/"+String.valueOf(caseId) + "/");
            System.out.println(newDir);
            newDir.mkdirs();

            int runCount = 0;
            
            while (runCount < runs) {
                lastTime = System.currentTimeMillis();
                App.main(c.input);
                System.out.println("TimeStep: " + (System.currentTimeMillis() - lastTime));
                File[] images = dir.getParentFile().listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.endsWith(".png")||name.equals("config.attr");
                    }
                });
                File runDir = new File(newDir, String.valueOf(runCount) + "/");
                runDir.mkdirs();
                for (File image : images) {
                    image.renameTo(new File(runDir, image.getName()));
                }
                
                runCount++;
            }
            caseId++;
        }
    }

    private static String unmarshallProtocol(String protocol) {
        if (protocol.equals("GenRep")) {
            return FullGenerationReplacement.class.getName();
        } else if (protocol.equals("GenMix")) {
            return GenerationalMixing.class.getName();
        } else if (protocol.equals("OverProd")) {
            return OverProduction.class.getName();
        } else {
            throw new RuntimeException("Did not recognize protocol: " + protocol);
        }
    }

    private static String unmarshallMechanism(String mechanism) {
        if (mechanism.equals("Fitness")) {
            return FitnessProportionateMechanism.class.getName();
        } else if (mechanism.equals("Rank")) {
            return RankMechanism.class.getName();
        } else if (mechanism.equals("Sigma")) {
            return SigmaScalingMechanism.class.getName();
        } else if (mechanism.equals("Tourna")) {
            return TournamentSelectionMechanism.class.getName();
        } else {
            throw new RuntimeException("Did not recognize meachanism: " + mechanism);
        }
    }

    private static String unmarshallSDM(String sdm) {
        if (sdm.equals("Interval")) {
            return SpikeInterval.class.getName();
        } else if (sdm.equals("Time")) {
            return SpikeTime.class.getName();
        } else if (sdm.equals("Wave")) {
            return Waveform.class.getName();
        } else {
            throw new RuntimeException("Did not recognize sdm: " + sdm);
        }
    }

    private static String unmarshallDataset(String set) {
        String[] izzy = {"./data/izzy-train1.dat",
            "./data/izzy-train2.dat",
            "./data/izzy-train3.dat",
            "./data/izzy-train4.dat"};
        return izzy[Integer.parseInt(set) - 1];

    }

    private static Case[] retainBest(File[] typeFiltered, int retain) {
        Pattern pattern = Pattern.compile("(\\d{1,4})-izzy(\\d)-(\\d*?)gen-(\\d*?)pop-(\\d*?)tour-(\\d\\.\\d*)eps-(\\d\\.\\d*)cr-(\\d\\.\\d*)mr-(\\d*?)acc-(\\w*)-(\\w*)-(\\w*)\\.png");
        Case[] cases = new Case[typeFiltered.length];
        int caseId = 0;
        for (File file : typeFiltered) {
            Matcher m = pattern.matcher(file.getName());
            m.find();
            //        1: 0              fitness             
            //        2: 1              izzyset             needed
            //        3: 1000           generation
            //        4: 75             population          needed
            //        5: 20             tournamentsize      needed
            //        6: 0.05           tournamenteps       needed
            //        7: 0.5            cr                  needed
            //        8: 0.2            mr                  needed
            //        9: 30             acc
            //        10: GenMix        protocol            needed
            //        11: Tourna        mechanism           needed
            //        12: Interval      sdm                 needed
            String fitness = m.group(1);
            String[] input = {
                unmarshallProtocol(m.group(10)),
                unmarshallMechanism(m.group(11)),
                m.group(5),
                m.group(6),
                m.group(7),
                m.group(8),
                unmarshallSDM(m.group(12)),
                m.group(4),
                unmarshallDataset(m.group(2))
            };
            cases[caseId++] = new Case(fitness, input);
        }
        Arrays.sort(cases);
        System.out.println("Best: " + cases[0].fitness);
        System.out.println("Worst: " + cases[cases.length - 1].fitness);
        Case[] retains = new Case[retain];
        System.arraycopy(cases, 0, retains, 0, retain);
        return retains;
    }
}
