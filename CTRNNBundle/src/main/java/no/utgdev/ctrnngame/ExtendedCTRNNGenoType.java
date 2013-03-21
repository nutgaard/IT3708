/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ctrnngame;

import java.util.Arrays;

/**
 *
 * @author Nicklas
 */
public class ExtendedCTRNNGenoType extends CTRNNGenoType {

    public ExtendedCTRNNGenoType(boolean[] vector) {
        super(vector);
    }
    
    public CTRNNPhenoType develop() {
        int[] step = Utils.translate(vector, 8);
        double[] data = new double[36];
        int dataInd = 0;
        for (int i = 0; i < 4; i++) {
            //translate gains
            data[dataInd++] = gainRange.getValue0()+(step[i]*Math.abs((gainRange.getValue0() - gainRange.getValue1())/265));
        }
        for (int i = 4; i < 8; i++) {
            //translate tau
            data[dataInd++] = tauRange.getValue0()+(step[i]*Math.abs((tauRange.getValue0() - tauRange.getValue1())/265));
        }
        for (int i = 8; i < 32; i++) {
            //translate normal arcs
            data[dataInd++] = weightRange.getValue0()+(step[i]*Math.abs((weightRange.getValue0() - weightRange.getValue1())/265));
        }
        for (int i = 32; i < 36; i++) {
            //translate bias arcs
            data[dataInd++] = biasRange.getValue0()+(step[i]*Math.abs((biasRange.getValue0() - biasRange.getValue1())/265));
        }
        return new CTRNNPhenoType(this, data);
    }

    public ExtendedCTRNNGenoType crossover(ExtendedCTRNNGenoType other) {
        if (vector.length != other.vector.length) {
            throw new ArrayIndexOutOfBoundsException("Unequal length of bitvectors");
        }
        int pivot = 1+(int)(Math.random()*35)*8;
        boolean[] n = new boolean[vector.length];
        if (Math.random() > 0.5) {
            System.arraycopy(vector, 0, n, 0, pivot);
            System.arraycopy(other.vector, pivot, n, pivot, n.length - pivot);
        } else {
            System.arraycopy(other.vector, 0, n, 0, pivot);
            System.arraycopy(vector, pivot, n, pivot, n.length - pivot);
        }
        return new ExtendedCTRNNGenoType(n);
    }

    public ExtendedCTRNNGenoType mutate() {
        boolean[] n = Arrays.copyOf(vector, vector.length);
        for (int i = 0; i < vector.length; i++) {
            if (Math.random() < mutationRate) {
                n[i] ^= true;
            }
        }
        return new ExtendedCTRNNGenoType(n);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExtendedCTRNNGenoType{");
        sb.append("vector=");
        for (boolean b : vector) {
            sb.append(((b) ? "1" : "0"));
        }
        sb.append("}");
        return sb.toString();
    }
    
}
