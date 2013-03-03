/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection;

import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.Population;

/**
 *
 * @author Nicklas
 */
public class SelectionStrategy {

    private SelectionProtocol protocol;
    private SelectionMechanism mechanism;

    public SelectionStrategy(SelectionProtocol selectionProtocol, SelectionMechanism selectionMechanism) {
        this.protocol = selectionProtocol;
        this.mechanism = selectionMechanism;
    }

    public SelectionProtocol getProtocol() {
        return protocol;
    }

    public SelectionMechanism getMechanism() {
        return mechanism;
    }

    public Population selection(Population adult, Population children, int retain, FitnessHandler fitnessHandler) {
        Population p = protocol.selection(adult, children, fitnessHandler, retain);
        p = mechanism.selection(p, fitnessHandler, retain);
        return p;
    }
}
