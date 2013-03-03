/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.selection.range;

import java.util.Comparator;

/**
 *
 * @author Nicklas
 */
public class Range {

    public static class RangeComparator implements Comparator<Range> {

        public int compare(Range o1, Range o2) {
            return (int)Math.signum(o1.start-o2.start);
        }
    }
    private double start, end;

    public Range(double start, double end) {
        if (start <= end) {
            this.start = start;
            this.end = end;
        } else {
            this.start = end;
            this.end = start;
        }
    }

    public boolean isWithin(double v) {
        return start <= v && v < end;
    }
    public double distance(double v) {
        return Math.min(Math.abs(start-v), Math.abs(end-v));
    }
    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }
    public void normalizationFactor(double n){
        this.start /= n;
        this.end /= n;
    }

    @Override
    public String toString() {
        return "Range{" + "start=" + start + ", end=" + end + ", size=" + (end-start) + "}";
    }
    
}
