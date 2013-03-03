/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population;

import no.utgdev.ga.core.GALoop;

/**
 *
 * @author Nicklas
 */
public abstract class PopulationGenerator<T extends PhenoType> {
    protected GALoop ga;

    public PopulationGenerator(GALoop ga) {
        this.ga = ga;
    }
    
    
    
    public abstract Population<T> create(int count);
}
