/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ctrnngame.drawers.autotest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import no.utgdev.ga.core.GALoop;
import org.javatuples.Triplet;

/**
 *
 * @author Nicklas
 */
public class FindBest {

    public static void main(String[] args) throws Exception {
        System.out.println("Parsing 1");
        parse("./images/");
    }

    private static void parse(String rerundata) throws Exception {
        int NOF_cases = 20, NOF_reruns = 20;
        GALoop ga = new GALoop(null);
        File dir = new File(rerundata);
        Pattern p = Pattern.compile("([\\d\\.]*?)-(\\d*?)gen-(\\d*?)pop-([\\d\\.]*?)tour-([\\d\\.]*?)eps-([\\d\\.]*?)cr-([\\d\\.]*?)mr-(\\w*)-(\\w*)-?\\.png");
        String[] caseData = new String[NOF_cases];
        List<Case> bestAvg = new LinkedList<Case>();
        Triplet<Double, Integer, Integer> globalBest = new Triplet<Double, Integer, Integer>(-Double.MAX_VALUE, -1, -1);
        Triplet<Double, Integer, Integer> globalWorst = new Triplet<Double, Integer, Integer>(Double.MAX_VALUE, -1, -1);
        Triplet<Double, Double, Integer> globalBestAvg = new Triplet<Double, Double, Integer>(-Double.MAX_VALUE, 0., -1);
        Triplet<Double, Double, Integer> globalWorstAvg = new Triplet<Double, Double, Integer>(Double.MAX_VALUE, 0., -1);
        for (int caseId = 0; caseId < NOF_cases; caseId++) {
            double best = -Double.MIN_VALUE, worst = Double.MAX_VALUE, avg = 0, std = 0;
            int bestRun = -1, worstRun = -1;
            double[] data = new double[NOF_reruns];
            int noimages = 0;
            Matcher paramFinder = null;
            for (int runId = 0; runId < NOF_reruns; runId++) {
                File image = new File(dir, caseId + "/" + runId + "/");
                if (image.listFiles().length == 0) {
                    System.out.println("Found no image in "+image);
                    noimages++;
                    continue;
                }
                String imageName = image.listFiles()[0].getName();
                Matcher fitnessfinder = p.matcher(imageName);
                if (paramFinder == null) {
                    paramFinder = p.matcher(imageName);
                    paramFinder.find();
                }
                fitnessfinder.find();
                double fit = Double.parseDouble(fitnessfinder.group(1));
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
            avg /= (20-noimages);
            for (double d : data) {
                std += Math.pow(d - avg, 2);
            }
            std /= (20-noimages);
            std = Math.sqrt(std);
            if (avg > globalBestAvg.getValue0()) {
                globalBestAvg = new Triplet<Double, Double, Integer>(avg, std, caseId);
            }
            if (avg < globalWorstAvg.getValue0()) {
                globalWorstAvg = new Triplet<Double, Double, Integer>(avg, std, caseId);
            }
            caseData[caseId] = genLocalString(caseId, bestRun, best, worstRun, worst, avg, std);
            
            String fitness = String.valueOf(avg);
            String[] input = {
                String.valueOf(caseId),
                paramFinder.group(8),
                paramFinder.group(9),
                paramFinder.group(4),
                paramFinder.group(5),
                paramFinder.group(6),
                paramFinder.group(7),
                paramFinder.group(3)
            };
            bestAvg.add(new Case(fitness, input));
            
        }
        File saveTo = new File(dir.getParentFile(), "summary.txt");
        String global = genGlobalString(globalBest, globalWorst, globalBestAvg, globalWorstAvg);
        Collections.sort(bestAvg);
        saveTo(saveTo, global, bestAvg, caseData);
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

    private static void saveTo(File file, String global, List<Case> bestAvg, String[] local) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.append(global);
        
        bw.append("Sorted averages\n");
        for (Case c : bestAvg) {
            bw.append("[").append(c.input[0]).append("]\n");
            bw.append("	Fitness: ").append(c.fitness).append("\n");
            bw.append("	Params: ").append(Arrays.toString(Arrays.copyOfRange(c.input, 1, c.input.length))).append("\n");
        }
        
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
