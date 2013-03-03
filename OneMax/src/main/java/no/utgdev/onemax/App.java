package no.utgdev.onemax;

import no.utgdev.ga.core.population.binary.BinaryPopulationCreator;
import no.utgdev.ga.core.fitness.BinaryFitnessHandler;
import java.util.Properties;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.selection.mechanism.AllMechanism;
import no.utgdev.ga.core.selection.mechanism.TournamentSelectionMechanism;
import no.utgdev.ga.core.selection.protocol.AllProtocol;
import no.utgdev.ga.core.selection.protocol.GenerationalMixing;
import no.utgdev.ga.utils.AskingProperties;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("core.population.generator", BinaryPopulationCreator.class.getName());
        properties.setProperty("core.fitness.handler", BinaryFitnessHandler.class.getName());
        properties.setProperty("core.strategy.adult.protocol", GenerationalMixing.class.getName());
        properties.setProperty("core.strategy.adult.mechanism", AllMechanism.class.getName());
        properties.setProperty("core.strategy.parent.protocol", AllProtocol.class.getName());
        properties.setProperty("core.strategy.parent.mechanism", TournamentSelectionMechanism.class.getName());
        properties.setProperty("core.generation.size", "750");
        properties.setProperty("core.population.size", "30");
        properties.setProperty("tournament.size", "20");
        properties.setProperty("tournament.eps", "0.05");
        properties.setProperty("core.statistics.handler", Plotting.class.getName());
        properties.setProperty("binary.length", "320");
//        properties.setProperty("binary.target", "10101001010");
        properties.setProperty("core.indicidual.crossover_rate", "0.9");
        properties.setProperty("core.indicidual.mutation_rate", "0.1");
        
//        AskingProperties ask = new AskingProperties(properties);
//        properties = ask.ask();
        
        GALoop ga = new GALoop(properties);
        ga.run();
    }
}
