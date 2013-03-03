/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.statistics;

import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.Population;

/**
 *
 * @author Nicklas
 */
public interface StatisticsHandler {
    public void generation(int genNo, Population population, FitnessHandler fitnessHandler);
}
