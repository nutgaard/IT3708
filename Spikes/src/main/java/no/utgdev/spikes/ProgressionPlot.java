/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes;

import java.io.File;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.statistics.StatisticsHandler;
import no.utgdev.spikes.plotting.SpikeTrainPlotter;
import no.utgdev.spikes.spiketrain.RawSpikeTrain;
import no.utgdev.spikes.spiketrain.SpikeTrainFromFile;

/**
 *
 * @author Nicklas
 */
public class ProgressionPlot extends StatisticsHandler {

    private SpikeTrainPlotter plotter;
    private RawSpikeTrain target;

    public ProgressionPlot(GALoop ga) {
        super(ga);
        this.target = new SpikeTrainFromFile(ga).generate(new File(ga.getProperties().getProperty("spike.target.uri")));
        this.plotter = new SpikeTrainPlotter(target);
    }

    public void generation(int genNo, Population population, FitnessHandler fitnessHandler) {
        Population<SpikePhenoType> best = (Population) population.best(1, fitnessHandler).getValue0();
        plotter.setSeries(target, best.get(0).getSpikeTrain());
    }
}
