/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import no.utgdev.ga.core.GALoop;
import no.utgdev.spikes.spiketrain.RawSpikeTrain;
import no.utgdev.spikes.spiketrain.SpikeTrainFromFile;
import no.utgdev.spikes.spiketrain.SpikeTrainFromParameters;
import no.utgdev.spikes.spiketrain.TimingSpikeTrain;
import no.utgdev.spikes.spiketrain.distancemetric.DistanceMetric;
import no.utgdev.spikes.spiketrain.distancemetric.SpikeInterval;
import no.utgdev.spikes.spiketrain.distancemetric.SpikeTime;
import no.utgdev.spikes.spiketrain.distancemetric.Waveform;
import org.javatuples.Pair;
import org.javatuples.Sextet;
import org.javatuples.Triplet;

/**
 *
 * @author Nicklas
 */
public class FindBest {

    static int N = 1000;
    static DistanceMetric dm = new SpikeInterval(), dm2 = new SpikeTime(), dm3 = new Waveform();
    static String[] izzy = {"./data/izzy-train1.dat",
        "./data/izzy-train2.dat",
        "./data/izzy-train3.dat",
        "./data/izzy-train4.dat"};

    public static void main(String[] args) throws Exception {
        System.out.println("Parsing 1");
        parse(1);
        System.out.println("Parsing 2");
        parse(2);
        System.out.println("Parsing 3");
        parse(3);
        System.out.println("Parsing 4");
        parse(4);
    }

    private static void parse(int izzydata) throws Exception {
        GALoop ga = new GALoop(null);
        File dir = new File("./backup/izzy" + izzydata + "/configs/");
        RawSpikeTrain targetRaw = new SpikeTrainFromFile(ga).generate(new File(izzy[izzydata - 1]));
        SpikeTrainFromParameters generator = new SpikeTrainFromParameters(ga);
        TimingSpikeTrain target = new TimingSpikeTrain(ga, targetRaw);
        Pattern p = Pattern.compile("\\w:\\s(\\S*)\\s\\w:\\s(\\S*)\\s\\w:\\s(\\S*)\\s\\w:\\s(\\S*)\\s\\w:\\s(\\S*)");
        String[] caseData = new String[20];
        Triplet<Double, Integer, Integer> globalBest = new Triplet<Double, Integer, Integer>(-Double.MAX_VALUE, -1, -1);
        Triplet<Double, Integer, Integer> globalWorst = new Triplet<Double, Integer, Integer>(Double.MAX_VALUE, -1, -1);
        Triplet<Double, Double, Integer> globalBestAvg = new Triplet<Double, Double, Integer>(-Double.MAX_VALUE, 0., -1);
        Triplet<Double, Double, Integer> globalWorstAvg = new Triplet<Double, Double, Integer>(Double.MAX_VALUE, 0., -1);
        for (int caseId = 0; caseId < 20; caseId++) {
            double best = -Double.MIN_VALUE, worst = Double.MAX_VALUE, avg = 0, std = 0;
            int bestRun = -1, worstRun = -1;
            double[] data = new double[20];
            for (int runId = 0; runId < 20; runId++) {
                File config = new File(dir, caseId + "/" + runId + "/config.attr");
                BufferedReader br = new BufferedReader(new FileReader(config));
                Matcher m = p.matcher(br.readLine());
                m.find();
//                1: 0.05386594320833683      a
//                2: 0.020197182558476925     b
//                3: -39.98339322395623       c
//                4: 5.056005830597132        d
//                5: 0.041449071355164055     k
                Sextet<Integer, Double, Double, Double, Double, Double> d = new Sextet<Integer, Double, Double, Double, Double, Double>(
                        N,
                        Double.parseDouble(m.group(1)),
                        Double.parseDouble(m.group(2)),
                        Double.parseDouble(m.group(3)),
                        Double.parseDouble(m.group(4)),
                        Double.parseDouble(m.group(5)));
                double fit = fitness(target, new TimingSpikeTrain(ga, generator.generate(d)));
                data[runId] = fit;
                if (fit > best) {
                    best = fit;
                    bestRun = runId;
                }
                if (fit < worst) {
                    worst = fit;
                    worstRun = runId;
                }
                if (fit > globalBest.getValue0()) {
                    globalBest = new Triplet<Double, Integer, Integer>(fit, caseId, runId);
                }
                if (fit < globalWorst.getValue0()) {
                    globalWorst = new Triplet<Double, Integer, Integer>(fit, caseId, runId);
                }
            }
            for (double d : data) {
                avg += d;
            }
            avg /= 20;
            for (double d : data) {
                std += Math.pow(d - avg, 2);
            }
            std /= 20;
            std = Math.sqrt(std);
            if (avg > globalBestAvg.getValue0()){
                globalBestAvg = new Triplet<Double, Double, Integer>(avg, std, caseId);
            }
            if (avg < globalWorstAvg.getValue0()){
                globalWorstAvg = new Triplet<Double, Double, Integer>(avg, std, caseId);
            }
            caseData[caseId] = genLocalString(caseId, bestRun, best, worstRun, worst, avg, std);
        }
        File saveTo = new File(dir.getParentFile(), "summary.txt");
        String global = genGlobalString(globalBest, globalWorst, globalBestAvg, globalWorstAvg);
        saveTo(saveTo, global, caseData);
    }

    private static double fitness(TimingSpikeTrain target, TimingSpikeTrain raw) {
//        return 1/(dm.distanceMetric(target, raw)+1);
        
        return 1/((dm.distanceMetric(target, raw) + 1)
                * (dm2.distanceMetric(target, raw) + 1)
                * (dm3.distanceMetric(target, raw) + 1)+1);
    }

    private static String genLocalString(int runId, int bestRun, double bestFit, int worstRun, double worstFit, double avg, double sd) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(runId).append("]\n");
        sb.append(" best:\n");
        sb.append("     runId: ").append(bestRun).append("\n");
        sb.append("     fitness: ").append(bestFit).append("\n");
        sb.append(" worst:\n");
        sb.append("     runId: ").append(worstRun).append("\n");
        sb.append("     fitness: ").append(worstFit).append("\n");
        sb.append(" average: ").append(avg).append("\n");
        sb.append(" sd: ").append(sd).append("\n");
        return sb.toString();
    }

    private static void saveTo(File file, String global, String[] local) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.append(global);
        for (String l : local) {
            bw.append(l);
        }
        bw.flush();
        bw.close();
    }

    private static String genGlobalString(Triplet<Double, Integer, Integer> globalBest, Triplet<Double, Integer, Integer> globalWorst, Triplet<Double, Double, Integer> globalBestAvg, Triplet<Double, Double, Integer> globalWorstAvg) {
        StringBuilder sb = new StringBuilder();
        sb.append("[global]\n");
        sb.append(" best:\n");
        sb.append("     caseId: ").append(globalBest.getValue1()).append("\n");
        sb.append("     runId: ").append(globalBest.getValue2()).append("\n");
        sb.append("     fitness: ").append(globalBest.getValue0()).append("\n");
        sb.append(" avg_best:\n");
        sb.append("     caseId: ").append(globalBestAvg.getValue2()).append("\n");
        sb.append("     average: ").append(globalBestAvg.getValue0()).append("\n");
        sb.append("     sd: ").append(globalBestAvg.getValue1()).append("\n");
        sb.append(" worst:\n");
        sb.append("     caseId: ").append(globalWorst.getValue1()).append("\n");
        sb.append("     runId: ").append(globalWorst.getValue2()).append("\n");
        sb.append("     fitness: ").append(globalWorst.getValue0()).append("\n");
        sb.append(" avg_worst:\n");
        sb.append("     caseId: ").append(globalWorstAvg.getValue2()).append("\n");
        sb.append("     average: ").append(globalWorstAvg.getValue0()).append("\n");
        sb.append("     sd: ").append(globalWorstAvg.getValue1()).append("\n");

        return sb.toString();
    }
}
