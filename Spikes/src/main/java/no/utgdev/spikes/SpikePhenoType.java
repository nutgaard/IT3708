/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes;

import java.util.Arrays;
import java.util.Properties;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.utils.TypedProperties;
import no.utgdev.spikes.spiketrain.SpikeTrainFromParameters;
import no.utgdev.spikes.spiketrain.TimingSpikeTrain;
import org.javatuples.Pair;
import org.javatuples.Quintet;
import org.javatuples.Sextet;

/**
 *
 * @author Nicklas
 */
public class SpikePhenoType extends PhenoType<SpikeGenoType> {

    private double[] param;
    private Quintet<Double, Double, Double, Double, Double> params;
    private TimingSpikeTrain spikeTrain;
    private static TypedProperties props;
    private static Pair<Double, Double>[] range;
    private static GALoop ga;
    

    public SpikePhenoType(SpikeGenoType genoType) {
        super(genoType);
        int[] paramsSize = Utils.translate(genoType.getVector(), genoType.getVector().length/5);
        int steps = (int) Math.pow(2, genoType.getVector().length / 5);
        param = new double[5];
        for (int i = 0; i < 5; i++) {
            param[i] = range[i].getValue0() + (paramsSize[i] * Math.abs((range[i].getValue0() - range[i].getValue1()) / steps));
//            System.out.println("Param: "+range[i].getValue0() +" "+paramsSize[i]+" " +(Math.abs((range[i].getValue0() - range[i].getValue1()) / steps)));
//            System.out.println(paramsSize[i]*(Math.abs((range[i].getValue0() - range[i].getValue1()) / steps)));
        }
    }
    public static void setParams(GALoop loop, Properties properties) {
        ga = loop;
        props = new TypedProperties(properties);
        range = new Pair[5];
        range[0] = new Pair<Double, Double>(props.getDouble("spike.a.min", 0.001), props.getDouble("spike.a.max", 0.2));
        range[1] = new Pair<Double, Double>(props.getDouble("spike.b.min", 0.01), props.getDouble("spike.b.max", 0.3));
        range[2] = new Pair<Double, Double>(props.getDouble("spike.c.min", -80.), props.getDouble("spike.c.max", -30.));
        range[3] = new Pair<Double, Double>(props.getDouble("spike.d.min", 0.1), props.getDouble("spike.d.max", 10));
        range[4] = new Pair<Double, Double>(props.getDouble("spike.k.min", 0.01), props.getDouble("spike.k.max", 1.0));
    }
    public double[] getParams() {
        return this.param;
    }
    public Quintet getConcParams() {
        if (this.params == null) {
            this.params = new Quintet<Double, Double, Double, Double, Double>(param[0], param[1], param[2], param[3], param[4]);
        }
        return this.params;
    }
    public TimingSpikeTrain getSpikeTrain() {
        if (this.spikeTrain == null) {
            Sextet s = new Sextet(
                    props.getInt("spike.length", 1000), 
                    param[0], param[1], 
                    param[2], param[3], 
                    param[4]
                    );
            this.spikeTrain = new TimingSpikeTrain(ga, new SpikeTrainFromParameters(ga).generate(s));
        }
        return this.spikeTrain;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SpikePhenoType{");
        sb.append("vector=");
        sb.append(Arrays.toString(param));
        sb.append("}");
        return sb.toString();
    }
}
