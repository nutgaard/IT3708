/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes;

/**
 *
 * @author Nicklas
 */
public class Utils {
    private static int[] binary = new int[]{
        1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 
        8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 
        2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 
        134217728, 268435456, 536870912, 1073741824
    };
    public static int[] translate(boolean[] b, int s) {
        int[] o = new int[b.length / s];

        for (int i = 0; i < o.length; i++) {
            int t = 0;
            for (int ss = s - 1; ss >= 0; ss--) {
                t += (b[i * s + ss] ? 1 : 0) * binary[s - ss-1];
            }
            o[i] = t;
        }
        return o;
    }
}
