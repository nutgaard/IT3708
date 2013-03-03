/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population.parser;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.PopulationParser;

/**
 *
 * @author Nicklas
 */
public class StandardDeviationFitness<S extends PhenoType> implements PopulationParser<Double, S> {

    public Double parse(Population<S> p, FitnessHandler fitnessHandler) {
        FitnessMap<S> generateFitnessMap = fitnessHandler.generateFitnessMap(p);
        return parse(p, generateFitnessMap);
    }

    public Double parse(Population<S> p, FitnessMap<S> fitnessMap) {
        double sum = 0;
        for (Entry<Double, List<S>> fitness : fitnessMap.entrySet()) {
            sum += fitness.getKey()*fitness.getValue().size();
        }
        double average = sum / p.size();
        double t = 0.0;
        for (Entry<Double, List<S>> fitness : fitnessMap.entrySet()) {
            t += Math.pow(fitness.getKey() - average, 2)*fitness.getValue().size();
        }
        t /= p.size();
        return Math.sqrt(t);
    }
}
