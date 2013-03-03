/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.statistics;

import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.Population;

/**
 *
 * @author Nicklas
 */
public class NullStatistics extends StatisticsHandler {

    public NullStatistics(GALoop ga) {
        super(ga);
    }
    
    

    public void generation(int genNo, Population population, FitnessHandler fitnessHandler) {
        
    }
}
