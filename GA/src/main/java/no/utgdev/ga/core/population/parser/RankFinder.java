/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population.parser;

import java.util.Collections;
import java.util.LinkedList;
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
public class RankFinder<S extends PhenoType> implements PopulationParser<List<PhenoType>, S> {

    public List<PhenoType> parse(Population<S> p, FitnessHandler fitnessHandler) {
        FitnessMap<S> generateFitnessMap = fitnessHandler.generateFitnessMap(p);
        return parse(p, generateFitnessMap);
    }

    public List<PhenoType> parse(Population<S> p, FitnessMap<S> fitnessMap) {
        List<PhenoType> list = new LinkedList<PhenoType>();
        for (Entry<Double, List<S>> e : fitnessMap.entrySet()) {
            list.addAll(e.getValue());
        }
        Collections.reverse(list);
        return list;
    }
}
