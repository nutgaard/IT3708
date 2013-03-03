package no.utgdev.spikes;

import java.util.Properties;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.selection.mechanism.AllMechanism;
import no.utgdev.ga.core.selection.mechanism.TournamentSelectionMechanism;
import no.utgdev.ga.core.selection.protocol.AllProtocol;
import no.utgdev.ga.core.selection.protocol.GenerationalMixing;
import no.utgdev.ga.utils.AskingProperties;
import no.utgdev.spikes.spiketrain.distancemetric.SpikeTime;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
               
        Properties properties = new Properties();
        properties.setProperty("core.population.generator", SpikePopulationCreator.class.getName());
        properties.setProperty("core.fitness.handler", SpikeFitnessHandler.class.getName());
        properties.setProperty("core.strategy.adult.protocol", GenerationalMixing.class.getName());
        properties.setProperty("core.strategy.adult.mechanism", AllMechanism.class.getName());
        properties.setProperty("core.strategy.parent.protocol", AllProtocol.class.getName());
        properties.setProperty("core.strategy.parent.mechanism", TournamentSelectionMechanism.class.getName());
        properties.setProperty("core.statistics.handler", Plotting.class.getName());
        properties.setProperty("core.generation.size", "1000");
        properties.setProperty("core.population.size", "20");
        properties.setProperty("tournament.size", "20");
        properties.setProperty("tournament.eps", "0.05");
        properties.setProperty("core.indicidual.crossover_rate", "0.9");
        properties.setProperty("core.indicidual.mutation_rate", "0.1");
        properties.setProperty("spike.param.accuracy", "30");
        properties.setProperty("spike.target.uri", "./data/izzy-train4.dat");
        properties.setProperty("spike.fitness.metric", SpikeTime.class.getName());
        properties.setProperty("spike.length", "1000");
        properties.setProperty("debug.generational_sysout", "100");
//        Same for a, b, c, d, k
//        properties.setProperty("spike.a.min", "0.001");
//        properties.setProperty("spike.a.max", "0.2");
        
        
        
        AskingProperties ask = new AskingProperties(properties);
        properties = ask.ask();
        GALoop ga = new GALoop(properties);
        
        SpikeGenoType.setParams(ga, properties);
        SpikePhenoType.setParams(ga, properties);        
        
        ga.run();
    }
}
