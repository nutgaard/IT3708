/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population.parser;

import java.util.List;
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
public class BestFitness<S extends PhenoType> implements PopulationParser<Double, S> {

    public Double parse(Population<S> p, FitnessHandler fitnessHandler) {
        FitnessMap<S> generateFitnessMap = fitnessHandler.generateFitnessMap(p);
        return parse(p, generateFitnessMap);
    }

    public Double parse(Population<S> p, FitnessMap<S> fitnessMap) {
        List<S> highKey = null;
        double low = -Double.MAX_VALUE;
        for (Entry<Double, List<S>> e : fitnessMap.entrySet()) {
            if (e.getKey() > low){
                low = e.getKey();
                highKey = e.getValue();
            }
        }
        return fitnessMap.get(highKey.get(0));
    }
    
}
