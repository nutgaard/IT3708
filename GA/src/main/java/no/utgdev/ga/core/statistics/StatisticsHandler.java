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
public abstract class StatisticsHandler {
    protected GALoop ga;

    public StatisticsHandler(GALoop ga) {
        this.ga = ga;
    }
    
    public abstract void generation(int genNo, Population population, FitnessHandler fitnessHandler);
}
