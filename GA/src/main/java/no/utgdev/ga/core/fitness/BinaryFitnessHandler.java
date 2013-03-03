/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.fitness;

import java.util.Arrays;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.binary.BinaryGenoType;
import no.utgdev.ga.core.population.binary.BinaryPhenoType;
import no.utgdev.ga.utils.TypedProperties;

/**
 *
 * @author Nicklas
 */
public class BinaryFitnessHandler extends FitnessHandler<BinaryGenoType, BinaryPhenoType> {

    private boolean[] goal;

    public BinaryFitnessHandler(GALoop ga, boolean[] goal) {
        super(ga);
        this.goal = goal;
    }

    public BinaryFitnessHandler(GALoop ga) {
        super(ga);
        TypedProperties props = new TypedProperties(ga.getProperties());
        String target = props.getString("binary.target", null);
        int binaryLength = props.getInt("binary.length", 8);
        System.out.println("Target: " + target);
        if (target == null) {
            goal = new boolean[binaryLength];
            Arrays.fill(goal, true);
        } else {
            char[] charArr = target.toCharArray();
            goal = new boolean[charArr.length];
            for (int i = 0; i < charArr.length; i++) {
                if (charArr[i] == '1') {
                    goal[i] = true;
                }
            }
        }
    }

    public FitnessMap<BinaryPhenoType> generateFitnessMap(Population<BinaryPhenoType> population) {
        FitnessMap<BinaryPhenoType> map = new FitnessMap<BinaryPhenoType>();
        for (BinaryPhenoType bpt : population) {
            map.put(bpt, getFitness(bpt, population));
        }
        return map;
    }

    public double getFitness(BinaryPhenoType phenotype, Population<BinaryPhenoType> population) {
        boolean[] vector = phenotype.getGenoType().getVector();
        if (vector.length != goal.length) {
            throw new RuntimeException("Vectorsize mismatch, expected " + goal.length + ", but was " + vector.length);
        }
        double trues = 0;
        for (int i = 0; i < goal.length; i++) {
            if (!(vector[i] ^ goal[i])) {
                trues++;
            }
        }
        return trues / vector.length;
    }
}
