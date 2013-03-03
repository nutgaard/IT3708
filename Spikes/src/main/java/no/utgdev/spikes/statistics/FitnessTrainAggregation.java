/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes.statistics;

import no.utgdev.ga.core.GALoop;

/**
 *
 * @author Nicklas
 */
public class FitnessTrainAggregation extends AggregatedStatisticsHandler {

    public FitnessTrainAggregation(GALoop ga) {
        super(ga, new Plotting(ga), new ProgressionPlot(ga));
    }
    
}
