/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ctrnngame;

import java.util.HashMap;
import java.util.Map;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.Population;

/**
 *
 * @author Nicklas
 */
public class CTRNNFitnessHandler extends FitnessHandler<CTRNNGenoType, CTRNNPhenoType> {
    private Map<double[], Double> mem;

    public CTRNNFitnessHandler(GALoop ga) {
        super(ga);
        mem = new HashMap<double[], Double>();
    }

    @Override
    public FitnessMap<CTRNNPhenoType> generateFitnessMap(Population<CTRNNPhenoType> population) {
        FitnessMap<CTRNNPhenoType> map = new FitnessMap<CTRNNPhenoType>();
        for (CTRNNPhenoType pheno : population) {
            double fitness;
            if (mem.containsKey(pheno.getData())){
                fitness = mem.get(pheno.getData());
            }else {
                fitness = getFitness(pheno, population);
                mem.put(pheno.getData(), fitness);
            }
            map.put(pheno, fitness);
        }
        return map;
    }

    @Override
    public double getFitness(CTRNNPhenoType phenotype, Population<CTRNNPhenoType> population) {
        ANNTrackerController controller = new ANNTrackerController(phenotype.getANN());
        return controller.run(40);
    }
    
}
