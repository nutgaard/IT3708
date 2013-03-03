/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population;

import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;

/**
 *
 * @author Nicklas
 */
public interface PopulationParser<T, S extends PhenoType> {

    public T parse(Population<S> p, FitnessHandler fitnessHandler);
    public T parse(Population<S> p, FitnessMap<S> fitnessMap);
}
