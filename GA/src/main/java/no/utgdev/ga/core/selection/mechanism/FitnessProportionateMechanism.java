/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.mechanism;

import java.util.Map;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.selection.range.Range;
import no.utgdev.ga.core.selection.range.RangeMap;

/**
 *
 * @author Nicklas
 */
public final class FitnessProportionateMechanism extends RangeBasedSelectionMechanism {

    public FitnessProportionateMechanism(GALoop ga) {
        super(ga);
    }
    
    @Override
    protected void createRangeMap(Population<PhenoType> population, FitnessHandler fitnessHandler) {
        this.range = new RangeMap<PhenoType>();
        FitnessMap<PhenoType> fitness = fitnessHandler.generateFitnessMap(population);
        double start = 0.0, end = 0.0;
        Range r;
        for (PhenoType pt : population) {
            double f = fitness.get(pt);
            f = (f != 0) ? f : Double.MIN_VALUE;
            end += f;
            r = new Range(start, end);
            PhenoType pre = range.put(r, pt);
            start += f;
        }
        range.normalize();
    }
}
