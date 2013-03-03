/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes;

import java.util.Arrays;
import java.util.Properties;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.population.GenoType;
import no.utgdev.ga.utils.TypedProperties;

/**
 *
 * @author Nicklas
 */
public class SpikeGenoType implements GenoType<SpikeGenoType, SpikePhenoType> {
    
    private static int paramsize;
    private static double mutationRate;
    
    private boolean[] vector;

    public SpikeGenoType(boolean[] vector) {
        this.vector = vector;
    }
    public static void setParams(GALoop ga, Properties properties) {
        TypedProperties props = new TypedProperties(properties);
        paramsize = props.getInt("spike.param.accuracy", 8);
        mutationRate = props.getDouble("core.indicidual.mutation_rate", 0.1);
    }

    public SpikePhenoType develop() {
        return new SpikePhenoType(this);
    }

    public boolean[] getVector() {
        return this.vector;
    }

    public SpikeGenoType crossover(SpikeGenoType other) {
        if (vector.length != other.vector.length) {
            throw new ArrayIndexOutOfBoundsException("Unequal length of bitvectors");
        }
        int[] possibleSplits = new int[]{
            paramsize,
            2*paramsize,
            3*paramsize,
            4*paramsize
        };
        
//        int pivot = (int) (Math.random() * vector.length);
        int pivot = possibleSplits[(int)(Math.random()*possibleSplits.length)];
        boolean[] n = new boolean[vector.length];
        if (Math.random() > 0.5) {
            System.arraycopy(vector, 0, n, 0, pivot);
            System.arraycopy(other.vector, pivot, n, pivot, n.length - pivot);
        } else {
            System.arraycopy(other.vector, 0, n, 0, pivot);
            System.arraycopy(vector, pivot, n, pivot, n.length - pivot);
        }
        return new SpikeGenoType(n);
    }

    public SpikeGenoType mutate() {
        boolean[] n = Arrays.copyOf(vector, vector.length);
        for (int i = 0; i < vector.length; i++) {
            if (Math.random() < mutationRate) {
                n[i] ^= true;
            }
        }
//        int ind = (int) (Math.random() * vector.length);
//        n[ind] ^= true;
        return new SpikeGenoType(n);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SpikeGenoType{");
        sb.append("vector=");
        for (boolean b : vector) {
            sb.append(((b) ? "1" : "0"));
        }
        sb.append("}");
        return sb.toString();
    }
}
