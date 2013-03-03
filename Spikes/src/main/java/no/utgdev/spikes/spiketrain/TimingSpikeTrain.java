/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes.spiketrain;

import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.utils.TypedProperties;

/**
 *
 * @author Nicklas
 */
public class TimingSpikeTrain extends RawSpikeTrain {

    private int[] timings;
    private double threshold;

    public TimingSpikeTrain(GALoop ga, RawSpikeTrain raw) {
        super(raw);
        TypedProperties props = new TypedProperties(ga.getProperties());
        threshold = props.getDouble("spiketrain.activation_threshold", 0.);
        generateSpikeTimings();
    }

    public int[] getTimings() {
        return timings;
    }

    private void generateSpikeTimings() {
        int window = 5;
        int paddSize = window / 2;
        double[] paddedRaw = new double[raw.length + 2 * paddSize];
//        List<Double> paddedRaw = new ArrayList<Double>(this);
        System.arraycopy(raw, 0, paddedRaw, paddSize, raw.length);
        for (int i = 0; i < paddSize; i++) {
            paddedRaw[i] = -Double.MAX_VALUE;
            paddedRaw[paddedRaw.length - 1 - i] = -Double.MAX_VALUE;
//            paddedRaw.add(i, -Double.MAX_VALUE);
//            paddedRaw.add(-Double.MAX_VALUE);
        }
        int[] timingsList = new int[raw.length];
        int timingsListInd = 0;
//        List<Integer> timingsList = new ArrayList<Integer>();
//        timingsList.add(0);
        timingsList[timingsListInd++] = 0;
        for (int wStart = 0, end = raw.length - window; wStart < end; wStart++) {
            int midInd = wStart + window / 2;
            double mid = raw[midInd];
            if (mid <= threshold) {
                continue;
            }
            boolean max = true;
            for (int offset = 0; offset < window / 2; offset++) {
                if (raw[midInd] < raw[midInd + offset] || raw[midInd] < raw[midInd - offset]) {
                    max = false;
                }
            }
            if (max) {
                timingsList[timingsListInd++] = midInd;
//                timingsList.add(midInd);
            }
        }
        timingsList[timingsListInd++] = raw.length;
//        timingsList.add(raw.length);
        timings = new int[timingsListInd];
//        int ii = 0;
//        for (int i : timingsList) {
//            timings[ii++] = i;
//        }
        System.arraycopy(timingsList, 0, timings, 0, timingsListInd);
    }
}
