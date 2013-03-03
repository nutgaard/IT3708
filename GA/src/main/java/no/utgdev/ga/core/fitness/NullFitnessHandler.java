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
public class NullFitnessHandler extends FitnessHandler<GenoType, PhenoType> {

    public NullFitnessHandler(GALoop ga) {
        super(ga);
    }
    
    

    public FitnessMap<PhenoType> generateFitnessMap(Population<PhenoType> population) {
        FitnessMap<PhenoType> map = new FitnessMap();
        for (PhenoType pt : population) {
            map.put(pt, 0.0);
        }
        return map;
    }

    public double getFitness(PhenoType phenotype, Population<PhenoType> population) {
        return 0.0;
    }

   
}
