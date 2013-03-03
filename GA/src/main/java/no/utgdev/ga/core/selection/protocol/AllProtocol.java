/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.protocol;

import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.selection.SelectionProtocol;

/**
 *
 * @author Nicklas
 */
public class AllProtocol extends SelectionProtocol{

    public AllProtocol(GALoop ga) {
        super(ga);
    }
    
    

    public Population selection(Population adults, Population children, FitnessHandler fitnessHandler, int retainNo) {
        return adults.merge(children);
    }

    public double generatationRatio() {
        return 1.0;
    }
    
}
