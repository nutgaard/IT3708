/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.mechanism;

import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.parser.AverageFitness;
import no.utgdev.ga.core.population.parser.StandardDeviationFitness;
import no.utgdev.ga.core.selection.range.Range;
import no.utgdev.ga.core.selection.range.RangeMap;

/**
 *
 * @author Nicklas
 */
public final class SigmaScalingMechanism extends RangeBasedSelectionMechanism {

    public SigmaScalingMechanism(GALoop ga) {
        super(ga);
    }
    
    

    protected void createRangeMap(Population<PhenoType> population, FitnessHandler fitnessHandler) {
        this.range = new RangeMap<PhenoType>();
        double start = 0.0, end = 0.0;
        FitnessMap<PhenoType> fitnessMap = fitnessHandler.generateFitnessMap(population);
        double avg = new AverageFitness().parse(population, fitnessMap);
        double sd = new StandardDeviationFitness().parse(population, fitnessMap);
        double step;
        Range r;
        for (PhenoType pt : population) {
            step = 1 + (fitnessMap.get(pt) - avg) / (2 * sd);
            end += step;
            r = new Range(start, end);
            range.put(r, pt);
            start += step;
        }
        range.normalize();
    }
}
