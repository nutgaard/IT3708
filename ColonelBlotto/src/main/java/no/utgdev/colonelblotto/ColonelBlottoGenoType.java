/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.colonelblotto;

import java.util.Arrays;
import no.utgdev.ga.core.population.GenoType;

/**
 *
 * @author Nicklas
 */
public class ColonelBlottoGenoType implements GenoType<ColonelBlottoGenoType, ColonelBlottoPhenoType> {
    private final boolean[] vector;
    private final int battleNo;
    
    public ColonelBlottoGenoType(boolean[] vector, int battleNo){
        this.vector = vector;
        this.battleNo = battleNo;
    }

    public ColonelBlottoPhenoType develop() {
        return new ColonelBlottoPhenoType(this);
    }

    public ColonelBlottoGenoType crossover(ColonelBlottoGenoType other) {
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
        return new ColonelBlottoGenoType(n, battleNo);
    }

    public ColonelBlottoGenoType mutate() {
        int ind = (int) (Math.random() * vector.length);
        boolean[] n = Arrays.copyOf(vector, vector.length);
        n[ind] ^= true;
        return new ColonelBlottoGenoType(n, battleNo);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ColonelBlottoGenoType{");
        sb.append("vector=");
        for (boolean b : vector) {
            sb.append(((b) ? "1" : "0"));
        }
        sb.append("}");
        return sb.toString();
    }
    public boolean[] getVector() {
        return this.vector;
    }

    public int getBattleNo() {
        return this.battleNo;
    }
}
