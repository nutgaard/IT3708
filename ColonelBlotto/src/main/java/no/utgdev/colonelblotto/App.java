package no.utgdev.colonelblotto;

import java.util.Properties;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.selection.mechanism.AllMechanism;
import no.utgdev.ga.core.selection.mechanism.TournamentSelectionMechanism;
import no.utgdev.ga.core.selection.protocol.AllProtocol;
import no.utgdev.ga.core.selection.protocol.GenerationalMixing;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("core.population.generator", ColonelBlottoPopulationCreator.class.getName());
        properties.setProperty("core.fitness.handler", ColonelBlottoFitnessHandler.class.getName());
        properties.setProperty("core.strategy.adult.protocol", GenerationalMixing.class.getName());
        properties.setProperty("core.strategy.adult.mechanism", AllMechanism.class.getName());
        properties.setProperty("core.strategy.parent.protocol", AllProtocol.class.getName());
        properties.setProperty("core.strategy.parent.mechanism", TournamentSelectionMechanism.class.getName());
        properties.setProperty("core.generation.size", "1000");
        properties.setProperty("core.population.size", "100");
        properties.setProperty("tournament.size", "20");
        properties.setProperty("tournament.eps", "0.2");
        properties.setProperty("core.statistics.handler", Plotting.class.getName());
        properties.setProperty("core.indicidual.crossover_rate", "0.9");
        properties.setProperty("core.indicidual.mutation_rate", "0.4");
        properties.setProperty("colonel_blotto.rf", ".5");
        properties.setProperty("colonel_blotto.lf", "0.5");
        properties.setProperty("colonel_blotto.S", "20");
        properties.setProperty("colonel_blotto.B", "5");
        GALoop ga = new GALoop(properties);
        ga.run();
    }
}
