/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ctrnngame;

import no.utgdev.ann.core.StructuredANN;
import no.utgdev.ga.core.population.PhenoType;

/**
 *
 * @author Nicklas
 */
public class CTRNNPhenoType extends PhenoType<CTRNNGenoType> {
    private double[] data;
    private StructuredANN ann;

    public CTRNNPhenoType(CTRNNGenoType genoType, double[] data) {
        super(genoType);
        this.data = data;
        this.ann = ANNBuilder.build(data);
    }
    public double[] getData() {
        return this.data;
    }
    public StructuredANN getANN() {
        return this.ann;
    }
}
