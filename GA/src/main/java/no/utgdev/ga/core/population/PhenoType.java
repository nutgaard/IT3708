/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population;

/**
 *
 * @author Nicklas
 */
public abstract class PhenoType<S extends GenoType> {
    protected S genoType;
    
    public PhenoType(S genoType) {
        this.genoType = genoType;
    }
    public S getGenoType() {
        return this.genoType;
    }
}
