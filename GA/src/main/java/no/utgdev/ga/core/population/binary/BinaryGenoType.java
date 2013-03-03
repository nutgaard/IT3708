/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population.binary;

import java.util.Arrays;
import no.utgdev.ga.core.population.GenoType;

/**
 *
 * @author Nicklas
 */
public class BinaryGenoType implements GenoType<BinaryGenoType, BinaryPhenoType> {

    private final boolean[] vector;

    public BinaryGenoType(boolean[] vector) {
        this.vector = vector;
    }

    public BinaryPhenoType develop() {
        return new BinaryPhenoType(this);
    }

    public boolean[] getVector() {
        return this.vector;
    }

    public BinaryGenoType crossover(BinaryGenoType other) {
        if (vector.length != other.vector.length) {
            throw new ArrayIndexOutOfBoundsException("Unequal length of bitvectors");
        }
        int pivot = (int) (Math.random() * vector.length);
        boolean[] n = new boolean[vector.length];
        if (Math.random() > 0.5) {
            System.arraycopy(vector, 0, n, 0, pivot);
            System.arraycopy(other.vector, pivot, n, pivot, n.length - pivot);
        } else {
            System.arraycopy(other.vector, 0, n, 0, pivot);
            System.arraycopy(vector, pivot, n, pivot, n.length - pivot);
        }
        return new BinaryGenoType(n);
    }

    public BinaryGenoType mutate() {
        int ind = (int) (Math.random() * vector.length);
        boolean[] n = Arrays.copyOf(vector, vector.length);
        n[ind] ^= true;
        return new BinaryGenoType(n);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BinaryGenoType{");
        sb.append("vector=");
        for (boolean b : vector) {
            sb.append(((b) ? "1" : "0"));
        }
        sb.append("}");
        return sb.toString();
    }
}
