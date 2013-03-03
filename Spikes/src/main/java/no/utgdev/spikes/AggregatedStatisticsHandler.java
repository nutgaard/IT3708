/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes;

import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.statistics.StatisticsHandler;

/**
 *
 * @author Nicklas
 */
public class AggregatedStatisticsHandler extends StatisticsHandler {

    protected StatisticsHandler[] handler;

    public AggregatedStatisticsHandler(GALoop ga, StatisticsHandler... handler) {
        super(ga);
        this.handler = handler;
    }

    public void generation(int genNo, Population population, FitnessHandler fitnessHandler) {
        for (StatisticsHandler sh : handler) {
            sh.generation(genNo, population, fitnessHandler);
        }
    }
}
