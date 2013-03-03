/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.mechanism;

import java.util.SortedMap;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.selection.SelectionMechanism;
import no.utgdev.ga.utils.TypedProperties;
import org.javatuples.Pair;

/**
 *
 * @author Nicklas
 */
public class TournamentSelectionMechanism extends SelectionMechanism {

    public TournamentSelectionMechanism(GALoop ga) {
        super(ga);
    }

    @Override
    public PhenoType getPhenoType(Population p, FitnessHandler fitnessHandler) {
        TypedProperties props = new TypedProperties(ga.getProperties());
        Population sub = p.subset(props.getInt("tournament.size", 20));
        if (Math.random() < props.getDouble("tournament.eps", 0.01)) {
            return sub.get(0);
        } else {
            Pair<Population, SortedMap<PhenoType, Double>> sorted = p.best(1, fitnessHandler);
            return sorted.getValue0().get(0);
        }
    }

    @Override
    public Population selection(Population p, FitnessHandler fitnessHandler, int retain) {
        if (p.size() == retain) {
            return p;
        }
        Pair<Population, SortedMap<PhenoType, Double>> sorted = p.best(retain, fitnessHandler);
        return sorted.getValue0();
    }
}
