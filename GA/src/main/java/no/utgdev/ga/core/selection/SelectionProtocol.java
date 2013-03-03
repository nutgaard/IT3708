/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection;

import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.Population;

/**
 *
 * @author Nicklas
 */
public abstract class SelectionProtocol {
    protected GALoop ga;

    public SelectionProtocol(GALoop ga) {
        this.ga = ga;
    }
    
    
    public abstract Population selection(Population adults, Population children, FitnessHandler fitnessHandler, int retainNo);
    public abstract double generatationRatio();
}
