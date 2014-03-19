/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ca.utils;

/**
 *
 * @author Nicklas
 */
public class BinaryUtils {
    public static boolean[] intToBooleanArray(int value, int arraySize) {
        boolean[] array = new boolean[arraySize];
        for (int i = 0; i  < arraySize; i++) {
            array[arraySize-1-i] = (1 << i & value) != 0;
        }
        return array;
    }
}
