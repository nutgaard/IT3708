/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.mechanism;

import java.util.List;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.parser.RankFinder;
import no.utgdev.ga.core.selection.range.Range;
import no.utgdev.ga.core.selection.range.RangeMap;
import no.utgdev.ga.utils.TypedProperties;

/**
 *
 * @author Nicklas
 */
public class RankMechanism extends RangeBasedSelectionMechanism {

    public RankMechanism(GALoop ga) {
        super(ga);
    }

    @Override
    protected void createRangeMap(Population<PhenoType> population, FitnessHandler fitnessHandler) {
        TypedProperties props = new TypedProperties(ga.getProperties());
        this.range = new RangeMap<PhenoType>();
        double min = props.getDouble("rank.min", 0.5), max = props.getDouble("rank.max", 1.5);
        int size = population.size();

        double start = 0.0, end = 0.0;
        double step;
        List<PhenoType> ranks = new RankFinder().parse(population, fitnessHandler);
        Range r;
        for (PhenoType pt : population) {
            step = min + (max - min) * ((ranks.indexOf(pt)) / (size - 1));
            end += step;
            r = new Range(start, end);
            range.put(r, pt);
            start += step;
        }
        range.normalize();
    }
}
