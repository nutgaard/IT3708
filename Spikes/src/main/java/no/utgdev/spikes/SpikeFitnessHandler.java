/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes;

import java.io.File;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.utils.TypedProperties;
import no.utgdev.spikes.spiketrain.SpikeTrainFromFile;
import no.utgdev.spikes.spiketrain.TimingSpikeTrain;
import no.utgdev.spikes.spiketrain.distancemetric.DistanceMetric;
import no.utgdev.spikes.spiketrain.distancemetric.SpikeTime;

/**
 *
 * @author Nicklas
 */
public class SpikeFitnessHandler extends FitnessHandler<SpikeGenoType, SpikePhenoType> {

    private static TypedProperties props;
    private static TimingSpikeTrain target;
    private static DistanceMetric metric;


    public SpikeFitnessHandler(GALoop ga) {
        super(ga);
        props = new TypedProperties(ga.getProperties());
        target = new TimingSpikeTrain(ga, new SpikeTrainFromFile(ga).generate(new File(props.getString("spike.target.uri", "./data/izzy-train1.dat"))));
        try {
            metric = (DistanceMetric) Class.forName(props.getString("spike.fitness.metric", SpikeTime.class.getName())).newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    

    public FitnessMap<SpikePhenoType> generateFitnessMap(Population<SpikePhenoType> population) {
        FitnessMap<SpikePhenoType> map = new FitnessMap<SpikePhenoType>();
        for (SpikePhenoType pheno : population) {
            map.put(pheno, getFitness(pheno, population));
        }
        return map;
    }

    public double getFitness(SpikePhenoType phenotype, Population<SpikePhenoType> population) {
        double m = metric.distanceMetric(target, phenotype.getSpikeTrain());
        return 1/m;
    }
}
