/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.mechanism;

import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.selection.SelectionMechanism;
import no.utgdev.ga.core.selection.SelectionProtocol;

/**
 *
 * @author Nicklas
 */
public class AllMechanism extends SelectionMechanism {

    public AllMechanism(GALoop ga) {
        super(ga);
    }
    
    
    
    @Override
    public PhenoType getPhenoType(Population p, FitnessHandler fitnessHandler) {
        return p.get((int)(Math.random()*p.size()));
    }

    @Override
    public Population selection(Population p, FitnessHandler fitnessHandler, int retain) {
        return p;
    }
    
}
