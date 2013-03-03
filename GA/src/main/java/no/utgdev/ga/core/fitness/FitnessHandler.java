/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.fitness;

import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.population.GenoType;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;

/**
 *
 * @author Nicklas
 */
public abstract class FitnessHandler<S extends GenoType, T extends PhenoType> {
    protected GALoop ga;

    public FitnessHandler(GALoop ga) {
        this.ga = ga;
    }
    
    
    public abstract FitnessMap<T> generateFitnessMap(Population<T> population);
    public abstract double getFitness(T phenotype, Population<T> population);
}
