/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes;

import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.PopulationParser;

/**
 *
 * @author Nicklas
 */
public class Entropy<S extends SpikePhenoType> implements PopulationParser<Double, SpikePhenoType> {

    public Double parse(Population<SpikePhenoType> p, FitnessHandler fitnessHandler) {
        return parse(p, (FitnessMap) null);
    }

    public Double parse(Population<SpikePhenoType> population, FitnessMap<SpikePhenoType> fitnessMap) {
        double sum = 0;
        for (SpikePhenoType p : population) {
            double[] troops = arrayAbs(p.getParams());
            int sumOfTroops = 0;
            double e = 0;
            double fractionInBattle;
            for (double troop : troops) {
                sumOfTroops += troop;
            }
            for (double troop : troops) {
                if (troop == 0) {
                    continue;
                }
                fractionInBattle = (troop*1.0)/sumOfTroops;
                e += fractionInBattle * (Math.log(fractionInBattle + 0.00000000000001) / Math.log(2));
            }
            sum += -e;
        }
        return sum / population.size();
    }
    private double[] arrayAbs(double[] d) {
        double[] o = new double[d.length];
        for (int i = 0; i < d.length; i++) {
            o[i] = Math.abs(d[i]);
        }
        return o;
    }
}
