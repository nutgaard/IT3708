/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ctrnngame;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import no.utgdev.ann.core.StructuredANN;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.selection.mechanism.AllMechanism;
import no.utgdev.ga.core.selection.mechanism.SigmaScalingMechanism;
import no.utgdev.ga.core.selection.protocol.AllProtocol;
import no.utgdev.ga.core.selection.protocol.GenerationalMixing;
import no.utgdev.trackergame.World;

/**
 *
 * @author Nicklas
 */
public class App {
    public static int doneCount = 0;
    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        //Do not touch
        properties.setProperty("core.population.generator", CTRNNPopulationCreator.class.getName());
        properties.setProperty("core.fitness.handler", CTRNNFitnessHandler.class.getName());
        properties.setProperty("core.statistics.handler", Plotting.class.getName());
        properties.setProperty("core.strategy.adult.mechanism", AllMechanism.class.getName());
        properties.setProperty("core.strategy.parent.protocol", AllProtocol.class.getName());
        properties.setProperty("core.generation.size", "1000");
        properties.setProperty("core.population.size", "100");
        properties.setProperty("debug.generational_sysout", "200");
        properties.setProperty("core.strategy.adult.protocol", GenerationalMixing.class.getName());
        properties.setProperty("core.strategy.parent.mechanism", SigmaScalingMechanism.class.getName());
        properties.setProperty("tournament.size", "75");
        properties.setProperty("tournament.eps", "0.1");
        properties.setProperty("core.individual.crossover_rate", "0.1");
        properties.setProperty("core.individual.mutation_rate", "0.1");
        
        if (args != null && args.length == 7) {
            properties.setProperty("core.strategy.adult.protocol", args[0]);
            properties.setProperty("core.strategy.parent.mechanism", args[1]);
            properties.setProperty("tournament.size", args[2]);
            properties.setProperty("tournament.eps", args[3]);
            properties.setProperty("core.individual.crossover_rate", args[4]);
            properties.setProperty("core.individual.mutation_rate", args[5]);
            properties.setProperty("core.population.size", args[6]);
        }

        GALoop ga = new GALoop(properties);
        CTRNNGenoType.setParams(ga, properties);
        FitnessMap<CTRNNPhenoType> fm = (FitnessMap<CTRNNPhenoType>) ga.run();
        List<CTRNNPhenoType> best = fm.get(fm.keySet().iterator().next());
        double fitnessBest = fm.get(best.get(0));
        System.out.println("Number of Top: " + best.size() + " Fitness: " + fm.get(best.get(0)));
        System.out.println(Arrays.toString(best.get(0).getData()));
//        ((Plotting) ga.getStatisticsHandler()).save(fileName(fitnessBest, properties, true), "Fitness: "+new DecimalFormat("##.##").format(fitnessBest));
        System.out.println("Done: "+(++doneCount));
        
        StructuredANN ann = ANNBuilder.build(best.get(0).getData());
                       ANNTrackerController tracker = new ANNTrackerController(ann);
                       World w = tracker.getWorld();
                       // while (in.next().length() > 0) {
                       while (true) {
                               tracker.printWorld();
                               Thread.sleep(300);
                               w.update();
                       }
    }
    private static String fileName(double prefix, Properties properties, boolean progression) {
        StringBuilder sb = new StringBuilder();
        sb.append("./images/");
        
        String n = String.valueOf(prefix);
        sb.append(n).append("-");
        sb.append(properties.getProperty("core.generation.size")).append("gen-");
        sb.append(properties.getProperty("core.population.size")).append("pop-");
        sb.append(properties.getProperty("tournament.size")).append("tour-");
        sb.append(properties.getProperty("tournament.eps")).append("eps-");
        sb.append(properties.getProperty("core.individual.crossover_rate")).append("cr-");
        sb.append(properties.getProperty("core.individual.mutation_rate")).append("mr-");

        String protocol = properties.getProperty("core.strategy.adult.protocol");
        if (protocol.contains("FullGenerationReplacement")) {
            sb.append("GenRep-");
        } else if (protocol.contains("GenerationalMixing")) {
            sb.append("GenMix-");
        } else if (protocol.contains("OverProduction")) {
            sb.append("OverProd-");
        }
        String mechanism = properties.getProperty("core.strategy.parent.mechanism");
        if (mechanism.contains("FitnessProportionateMechanism")) {
            sb.append("Fitness-");
        } else if (mechanism.contains("RankMechanism")) {
            sb.append("Rank-");
        } else if (mechanism.contains("SigmaScalingMechanism")) {
            sb.append("Sigma-");
        } else if (mechanism.contains("TournamentSelectionMechanism")) {
            sb.append("Tourna");
        }

        sb.append(".png");
        return sb.toString();
    }
}
