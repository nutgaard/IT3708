/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes.spiketrain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import no.utgdev.ga.core.GALoop;

/**
 *
 * @author Nicklas
 */
public class SpikeTrainFromFile extends SpikeTrainGenerator<File> {

    public SpikeTrainFromFile(GALoop ga) {
        super(ga);
    }

    public RawSpikeTrain generate(File datainput) {
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(datainput));
            String line;
            StringTokenizer st;
            List<Double> raw = new LinkedList<Double>();
            while ((line = br.readLine()) != null) {
                st = new StringTokenizer(line, " ");
                while (st.hasMoreTokens()) {
                    raw.add(Double.parseDouble(st.nextToken()));
                }
            }
            br.close();
            double[] r = new double[raw.size()];
            int rI = 0;
            for (Double d : raw) {
                r[rI++] = d;
            }
            return new RawSpikeTrain(r);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
