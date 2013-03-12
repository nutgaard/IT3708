/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ctrnngame;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.selection.mechanism.AllMechanism;
import no.utgdev.ga.core.selection.mechanism.SigmaScalingMechanism;
import no.utgdev.ga.core.selection.mechanism.TournamentSelectionMechanism;
import no.utgdev.ga.core.selection.protocol.AllProtocol;
import no.utgdev.ga.core.selection.protocol.GenerationalMixing;

/**
 *
 * @author Nicklas
 */
public class App {

    public static void main(String[] args) {
        Properties properties = new Properties();
        //Do not touch
        properties.setProperty("core.population.generator", CTRNNPopulationCreator.class.getName());
        properties.setProperty("core.fitness.handler", CTRNNFitnessHandler.class.getName());
        properties.setProperty("core.statistics.handler", Plotting.class.getName());
        properties.setProperty("core.strategy.adult.mechanism", AllMechanism.class.getName());
        properties.setProperty("core.strategy.parent.protocol", AllProtocol.class.getName());
        properties.setProperty("core.generation.size", "10000");
        properties.setProperty("core.population.size", "150");
        properties.setProperty("debug.generational_sysout", "100");
        properties.setProperty("core.strategy.adult.protocol", GenerationalMixing.class.getName());
        properties.setProperty("core.strategy.parent.mechanism", TournamentSelectionMechanism.class.getName());
        properties.setProperty("tournament.size", "25");
        properties.setProperty("tournament.eps", "0.1");
        properties.setProperty("core.individual.crossover_rate", "0.9");
        properties.setProperty("core.individual.mutation_rate", "0.35");
        
        GALoop ga = new GALoop(properties);
        CTRNNGenoType.setParams(ga, properties);
        FitnessMap<CTRNNPhenoType> fm = (FitnessMap<CTRNNPhenoType>) ga.run();
        List<CTRNNPhenoType> best = fm.get(fm.keySet().iterator().next());
        double fitnessBest = fm.get(best.get(0));
        System.out.println("Number of Top: " + best.size() + " Fitness: " + fm.get(best.get(0)));
        System.out.println(Arrays.toString(best.get(0).getData()));
    }
}
