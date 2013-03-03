/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.utils.TypedProperties;
import no.utgdev.spikes.spiketrain.SpikeTrainFromFile;
import no.utgdev.spikes.spiketrain.TimingSpikeTrain;
import no.utgdev.spikes.spiketrain.distancemetric.DistanceMetric;
import no.utgdev.spikes.spiketrain.distancemetric.SpikeTime;
import org.javatuples.Quintet;

/**
 *
 * @author Nicklas
 */
public class SpikeFitnessHandler extends FitnessHandler<SpikeGenoType, SpikePhenoType> {

    private static TypedProperties props;
    private static TimingSpikeTrain target;
    private static DistanceMetric metric;
    private static Map<Quintet<Double, Double, Double, Double, Double>, Double> mem;


    public SpikeFitnessHandler(GALoop ga) {
        super(ga);
        props = new TypedProperties(ga.getProperties());
        target = new TimingSpikeTrain(ga, new SpikeTrainFromFile(ga).generate(new File(props.getString("spike.target.uri", "./data/izzy-train1.dat"))));
        mem = new HashMap<Quintet<Double, Double, Double, Double, Double>, Double>();
        try {
            metric = (DistanceMetric) Class.forName(props.getString("spike.fitness.metric", SpikeTime.class.getName())).newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    

    public FitnessMap<SpikePhenoType> generateFitnessMap(Population<SpikePhenoType> population) {
        FitnessMap<SpikePhenoType> map = new FitnessMap<SpikePhenoType>();
        for (SpikePhenoType pheno : population) {
            double fitness;
            if (mem.containsKey(pheno.getConcParams())){
                fitness = mem.get(pheno.getConcParams());
            }else {
                fitness = getFitness(pheno, population);
                mem.put(pheno.getConcParams(), fitness);
            }
            map.put(pheno, fitness);
        }
        return map;
    }

    public double getFitness(SpikePhenoType phenotype, Population<SpikePhenoType> population) {
        double m = metric.distanceMetric(target, phenotype.getSpikeTrain());
        return 1/m;
    }
}
