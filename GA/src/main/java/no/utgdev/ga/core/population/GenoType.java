/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population;

/**
 *
 * @author Nicklas
 */
public interface GenoType<S extends GenoType, T extends PhenoType> {
    public T develop();
    public S crossover(S other);
    public S mutate();
}
