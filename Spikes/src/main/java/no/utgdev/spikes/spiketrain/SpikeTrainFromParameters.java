/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes.spiketrain;

import java.util.LinkedList;
import java.util.List;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.utils.TypedProperties;
import org.javatuples.Sextet;

/**
 *
 * @author Nicklas
 */
public class SpikeTrainFromParameters extends SpikeTrainGenerator<Sextet<Integer, Double, Double, Double, Double, Double>> {

    public SpikeTrainFromParameters(GALoop ga) {
        super(ga);
    }
    
    

    public RawSpikeTrain generate(Sextet<Integer, Double, Double, Double, Double, Double> datainput) {
        TypedProperties props = new TypedProperties(ga.getProperties());
        int N = datainput.getValue0();
        double a = datainput.getValue1();
        double b = datainput.getValue2();
        double c = datainput.getValue3();
        double d = datainput.getValue4();
        double k = datainput.getValue5();

        double v = -60;
        double u = 0;
        double threshold = props.getDouble("spiketrain.spike_threshold", 35);
        double tau = props.getDouble("spiketrain.tau", 10.);
        double tauInv = 1 / tau;
        double extIn = props.getDouble("spiketrain.externalInput", 10.);

        int n = 0;
        double[] raw = new double[N+1];
//        List<Double> raw = new LinkedList<Double>();
        while (n <= N) {
            v += tauInv * (k * v * v + 5 * v + 140 - u + extIn);
            u += a * tauInv * (b * v - u);
//            raw.add(v);
            if (v >= threshold) {
                raw[n] = threshold;
//                raw.add(threshold);
                v = c;
                u = u+d;
            }else {
                raw[n] = v;
//                raw.add(v);
            }
            n++;
        }

        return new RawSpikeTrain(raw);
    }
}
