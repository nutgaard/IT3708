/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population.binary;

import no.utgdev.ga.core.population.PhenoType;

/**
 *
 * @author Nicklas
 */
public class BinaryPhenoType extends PhenoType<BinaryGenoType> {

    public BinaryPhenoType(BinaryGenoType genoType) {
        super(genoType);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BinaryPhenoType{");
        sb.append("vector=");
        for (boolean b : genoType.getVector()) {
            sb.append(((b) ? "1" : "0"));
        }
        sb.append("}").append("#"+Integer.toHexString(hashCode()));
        return sb.toString();
    }
    
}
