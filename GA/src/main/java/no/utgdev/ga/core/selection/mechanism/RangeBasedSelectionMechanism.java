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
import no.utgdev.ga.core.selection.range.RangeMap;
import org.javatuples.Pair;

/**
 *
 * @author Nicklas
 */
public abstract class RangeBasedSelectionMechanism extends SelectionMechanism {

    protected RangeMap<PhenoType> range;
    protected Population lastPopulation;

    public RangeBasedSelectionMechanism(GALoop ga) {
        super(ga);
    }
    public RangeBasedSelectionMechanism(GALoop ga, RangeMap<PhenoType> range){
        super(ga);
        this.range = range;
    }

    protected abstract void createRangeMap(Population<PhenoType> population, FitnessHandler fitnessHandler);

    public PhenoType getPhenoType(Population p, FitnessHandler fitnessHandler) {
        if (lastPopulation != p) {
            createRangeMap(p, fitnessHandler);
            lastPopulation = p;
        }
        double v = Math.random() * range.lastEntry().getKey().getEnd();
        return range.findEntry(v).getValue();
    }

    public Population selection(Population p, FitnessHandler fitnessHandler, int retain) {
        if (p.size() <= retain) {
            return p;
        }
        Pair<Population, SortedMap<PhenoType, Double>> sorted = p.best(retain, fitnessHandler);
        return sorted.getValue0();
    }
    public RangeMap<PhenoType> getRangeMap() {
        return this.range;
    }
}
