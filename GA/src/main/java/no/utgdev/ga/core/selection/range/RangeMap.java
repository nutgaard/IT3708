package no.utgdev.ga.core.selection.range;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *
 * @author Nicklas
 */
public class RangeMap<T> extends TreeMap<Range, T> {

    public RangeMap() {
        super(new Range.RangeComparator());
    }

    public Entry<Range, T> findEntry(double v) {
        for (Entry<Range, T> e : entrySet()) {
            if (e.getKey().isWithin(v)) {
                return e;
            }
        }
        //Non found, find closest
        double[] distance = new double[this.size()];
        Entry<Range, T>[] cach = new Entry[this.size()];
        int d = 0;
        for (Entry<Range, T> e : entrySet()){
            distance[d] = e.getKey().distance(v);
            cach[d++] = e;
        }
        double min = Double.MAX_VALUE;
        d = 0;
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] < min) {
                min = distance[i];
                d = i;
            }
        }
        return cach[d];
    }
    public void normalize() {
        double greates = lastEntry().getKey().getEnd();
        for (Entry<Range, T> e : entrySet()){
            e.getKey().normalizationFactor(greates);
        }
    }
}
