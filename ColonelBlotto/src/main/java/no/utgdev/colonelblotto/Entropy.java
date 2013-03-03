/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.colonelblotto;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.PopulationParser;

/**
 *
 * @author Nicklas
 */
public class Entropy<S extends ColonelBlottoPhenoType> implements PopulationParser<Double, ColonelBlottoPhenoType> {

    public Double parse(Population<ColonelBlottoPhenoType> p, FitnessHandler fitnessHandler) {
        return parse(p, (FitnessMap) null);
    }

    public Double parse(Population<ColonelBlottoPhenoType> population, FitnessMap<ColonelBlottoPhenoType> fitnessMap) {
        double sum = 0;
        for (ColonelBlottoPhenoType p : population) {
            int[] troops = p.getTroops();
            int sumOfTroops = 0;
            double e = 0;
            double fractionInBattle;
            for (int troop : troops) {
                sumOfTroops += troop;
            }
            for (int troop : troops) {
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
}
