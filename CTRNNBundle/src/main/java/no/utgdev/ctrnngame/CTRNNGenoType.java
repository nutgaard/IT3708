/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ctrnngame;

import java.util.Arrays;
import java.util.Properties;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.population.GenoType;
import no.utgdev.ga.utils.TypedProperties;
import org.javatuples.Pair;

/**
 *
 * @author Nicklas
 */
public class CTRNNGenoType implements GenoType<CTRNNGenoType, CTRNNPhenoType> {
    private static final Pair<Double, Double> weightRange = new Pair<Double, Double>(-5.0, 5.0);
    private static final Pair<Double, Double> biasRange = new Pair<Double, Double>(-10.0, 0.0);
    private static final Pair<Double, Double> gainRange = new Pair<Double, Double>(1.0, 5.0);
    private static final Pair<Double, Double> tauRange = new Pair<Double, Double>(1.0, 2.0);
    private static double mutationRate;
    private final boolean[] vector; //272 bit long, 34 params, 8bit per param

    public CTRNNGenoType(boolean[] vector) {
        this.vector = vector;
    }
    public static void setParams(GALoop ga, Properties properties) {
        TypedProperties props = new TypedProperties(properties);
        mutationRate = props.getDouble("core.indicidual.mutation_rate", 0.1);
    }
    public CTRNNPhenoType develop() {
        int[] step = Utils.translate(vector, 8);
        double[] data = new double[34];
        int dataInd = 0;
        for (int i = 0; i < 4; i++) {
            //translate gains
            data[dataInd++] = gainRange.getValue0()+(step[i]*Math.abs((gainRange.getValue0() - gainRange.getValue1())/265));
        }
        for (int i = 4; i < 8; i++) {
            //translate tau
            data[dataInd++] = tauRange.getValue0()+(step[i]*Math.abs((tauRange.getValue0() - tauRange.getValue1())/265));
        }
        for (int i = 8; i < 30; i++) {
            //translate normal arcs
            data[dataInd++] = weightRange.getValue0()+(step[i]*Math.abs((weightRange.getValue0() - weightRange.getValue1())/265));
        }
        for (int i = 30; i < 34; i++) {
            //translate bias arcs
            data[dataInd++] = biasRange.getValue0()+(step[i]*Math.abs((biasRange.getValue0() - biasRange.getValue1())/265));
        }
        return new CTRNNPhenoType(this, data);
    }

    public CTRNNGenoType crossover(CTRNNGenoType other) {
        if (vector.length != other.vector.length) {
            throw new ArrayIndexOutOfBoundsException("Unequal length of bitvectors");
        }
        int pivot = 1+(int)(Math.random()*33)*8;
        boolean[] n = new boolean[vector.length];
        if (Math.random() > 0.5) {
            System.arraycopy(vector, 0, n, 0, pivot);
            System.arraycopy(other.vector, pivot, n, pivot, n.length - pivot);
        } else {
            System.arraycopy(other.vector, 0, n, 0, pivot);
            System.arraycopy(vector, pivot, n, pivot, n.length - pivot);
        }
        return new CTRNNGenoType(n);
    }

    public CTRNNGenoType mutate() {
        boolean[] n = Arrays.copyOf(vector, vector.length);
        for (int i = 0; i < vector.length; i++) {
            if (Math.random() < mutationRate) {
                n[i] ^= true;
            }
        }
        return new CTRNNGenoType(n);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CTRNNGenoType{");
        sb.append("vector=");
        for (boolean b : vector) {
            sb.append(((b) ? "1" : "0"));
        }
        sb.append("}");
        return sb.toString();
    }
    
}
