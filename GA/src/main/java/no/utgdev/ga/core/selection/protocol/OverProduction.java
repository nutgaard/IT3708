/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.protocol;

import java.util.SortedMap;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.selection.SelectionProtocol;
import org.javatuples.Pair;

/**
 *
 * @author Nicklas
 */
public class OverProduction extends SelectionProtocol {

    public OverProduction(GALoop ga) {
        super(ga);
    }

    public Population selection(Population adults, Population children, FitnessHandler fitnessHandler, int retainNo) {
        Pair<Population, SortedMap<PhenoType, Double>> p = children.best(retainNo, fitnessHandler);
        return p.getValue0();
    }

    public double generatationRatio() {
        return 1.5;
    }
}
