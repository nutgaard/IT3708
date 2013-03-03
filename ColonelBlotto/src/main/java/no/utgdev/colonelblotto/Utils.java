/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.colonelblotto;

/**
 *
 * @author Nicklas
 */
public class Utils {
    public static int[] translate(boolean[] b, int s) {
        int[] o = new int[b.length / s];

        for (int i = 0; i < o.length; i++) {
            int t = 0;
            for (int ss = s - 1; ss >= 0; ss--) {
                t += (b[i * s + ss] ? 1 : 0) * Math.pow(2, s - ss-1);
            }
            o[i] = t;
        }
        return o;
    }
}
