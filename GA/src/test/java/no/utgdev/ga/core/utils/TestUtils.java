/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.utils;

import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.binary.BinaryGenoType;
import no.utgdev.ga.core.population.binary.BinaryPhenoType;

/**
 *
 * @author Nicklas
 */
public class TestUtils {

    public static Population createPopulation(String[] binaryStrings) {
        BinaryPhenoType[] p = new BinaryPhenoType[binaryStrings.length];
        for (int i = 0; i < binaryStrings.length; i++) {
            p[i] = new BinaryPhenoType(new BinaryGenoType(stringToBooleanVector(binaryStrings[i])));
        }
        return new Population(p);
    }

    public static boolean[] stringToBooleanVector(String v) {
        boolean[] b = new boolean[v.length()];
        char[] vc = v.toCharArray();
        for (int i = 0; i < vc.length; i++) {
            b[i] = (vc[i] == '1');
        }
        return b;
    }

    public static String[] createDefaultPopulation() {
        return new String[]{
                    "11111100",
                    "11110000",
                    "11000000",
                    "00000000",
                    "11111111"
                };
    }

    public static String booleanVectorToString(boolean[] v) {
        StringBuilder sb = new StringBuilder();
        for (boolean b : v) {
            sb.append((b) ? '1' : '0');
        }
        return sb.toString();
    }
}
