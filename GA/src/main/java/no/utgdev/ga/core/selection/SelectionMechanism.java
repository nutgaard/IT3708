/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection;

import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import org.javatuples.Pair;

/**
 *
 * @author Nicklas
 */
public abstract class SelectionMechanism {    
    protected GALoop ga;

    public SelectionMechanism(GALoop ga) {
        this.ga = ga;
    }
    
    

    public Pair<PhenoType, PhenoType> getPhenoTypePair(Population p, FitnessHandler fitnessHandler) {
        return new Pair<PhenoType, PhenoType>(getPhenoType(p, fitnessHandler), getPhenoType(p, fitnessHandler));
    }

    public abstract PhenoType getPhenoType(Population p, FitnessHandler fitnessHandler);

    public abstract Population selection(Population p, FitnessHandler fitnessHandler, int retain);
}
