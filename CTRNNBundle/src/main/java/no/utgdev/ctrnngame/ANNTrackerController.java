/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ctrnngame;

import java.util.Arrays;
import java.util.Scanner;
import no.utgdev.ann.core.structured.StructuredANN;
import no.utgdev.trackergame.TrackController;
import no.utgdev.trackergame.World;

/**
 *
 * @author Nicklas
 */
public class ANNTrackerController implements TrackController {

//    private static double[] globalFactor = {0.0, 0.0, 0.0};
//    private static double globalSum = 0.0;
    private World world;
    private StructuredANN ann;
    private double[] score = {0.0, 0.0, 0.0};
    private int objectCount = 0;

    public ANNTrackerController(StructuredANN ann) {
        this.ann = ann;
        world = new World(30, 15, 5, this);
    }

    public double run(int NOF_objects) {
        while (objectCount < NOF_objects) {
            world.update();
        }
        double o = 0;
        double scale = 1./3;
        for (int i = 0; i < 3; i++) {
            o += scale * score[i];
        }
        System.out.println("Scales: "+scale+" Score: "+Arrays.toString(score)+" O: "+o);
        return o;
    }

    public int move(boolean[] shadows) {
        double[] input = new double[shadows.length];
        for (int i = 0; i < shadows.length; i++) {
            if (shadows[i]) {
                input[i] = 1.0;
            }
        }
        double[] output = ann.update(input);
        double diff = output[0] - output[1];
        double sign = Math.signum(diff);
        diff = Math.abs(diff);
        int move;
        if (diff <= 0.11111) {
            move = 0;
        } else if (diff <= 0.33333) {
            move = (int) (sign * 1);
        } else if (diff <= 0.55555) {
            move = (int) (sign * 2);
            score[2] += 0.001;
        } else if (diff <= 0.77777) {
            move = (int) (sign * 3);
            score[2] += 0.002;
        } else {
            move = (int) (sign * 4);
            score[2] += 0.003;
        }
        return move;
    }

    public void objectDone(double overlap, int objectSize) {
        objectCount++;
        if (objectSize < 5) {
            //Should catch them
            if (overlap == 1.0) {
                score[0] += 2;
            }
        } else {
            //Avoid them
            if (overlap == 0.0) {
                score[1] += 2;
            }
        }
    }

    public World getWorld() {
        return this.world;
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        double[] data = new double[]{
            //                        3.8075471698113206, 1.0754716981132075, 4.818867924528302, 2.7660377358490567, 1.6830188679245284, 1.0981132075471698, 1.0150943396226415, 1.3283018867924528, 4.09433962264151, 2.584905660377358, 4.283018867924527, -2.0943396226415096, -4.886792452830188, 1.3773584905660377, -1.150943396226415, -2.358490566037736, -1.6415094339622645, 2.1698113207547163, 2.735849056603773, 2.962264150943396, -0.9622641509433967, 2.3207547169811322, -4.283018867924528, 0.6981132075471699, -4.622641509433962, 3.0754716981132066, 2.849056603773585, 0.09433962264150964, 3.452830188679245, -0.09433962264150964, -3.283018867924529, -1.3962264150943398, -2.6792452830188678, -3.169811320754717
            //                        2.4943396226415095, 3.4452830188679244, 3.581132075471698, 1.1056603773584905, 1.490566037735849, 1.758490566037736, 1.2452830188679245, 1.0, -0.9245283018867925, 0.9622641509433958, 4.132075471698112, -1.1886792452830188, -0.5094339622641515, 1.8679245283018862, 1.0377358490566033, -0.3207547169811322, 4.547169811320755, -3.150943396226415, -1.6037735849056607, 2.584905660377358, 2.69811320754717, 0.9622641509433958, 4.547169811320755, -4.3584905660377355, 2.2452830188679247, -4.811320754716981, -1.150943396226415, -0.13207547169811296, 2.735849056603773, 1.4528301886792452, -4.679245283018868, -1.6603773584905657, -5.39622641509434, -0.4528301886792452
//                                    2.8867924528301887, 3.309433962264151, 1.4075471698113207, 1.9811320754716981, 1.5773584905660378, 1.3018867924528301, 1.539622641509434, 1.3735849056603775, 0.3207547169811322, -1.1886792452830188, 1.7547169811320753, 0.16981132075471717, -3.9056603773584904, 3.150943396226415, -1.6037735849056607, 4.584905660377357, 0.5094339622641506, -3.0754716981132075, 0.4716981132075473, 0.283018867924528, -4.2075471698113205, -1.867924528301887, 0.4716981132075473, 1.9056603773584904, -4.849056603773585, 0.39622641509433976, 0.3207547169811322, -3.9433962264150946, -4.660377358490566, 3.0, -4.264150943396227, -3.2075471698113214, -0.4528301886792452, -4.528301886792453
            //            4.562264150943396, 3.0830188679245283, 4.381132075471698, 4.109433962264151, 1.6792452830188678, 1.430188679245283, 1.569811320754717, 1.0264150943396226, -1.566037735849057, 2.283018867924528, 3.150943396226415, -4.9245283018867925, 3.0, 3.415094339622641, -0.16981132075471717, 3.9433962264150946, -4.245283018867925, 3.6037735849056602, -3.6037735849056602, -3.1132075471698113, 4.39622641509434, 3.679245283018867, 2.132075471698113, -4.69811320754717, -3.30188679245283, 1.5283018867924527, -4.9245283018867925, -3.830188679245283, -4.2075471698113205, -4.056603773584905, -7.773584905660377, -5.622641509433962, -7.773584905660377, -0.4905660377358494
//            1.6792452830188678, 3.339622641509434, 1.4377358490566037, 3.339622641509434, 1.8943396226415095, 1.6075471698113208, 1.1320754716981132, 1.060377358490566, 1.9056603773584904, 3.0754716981132066, -2.811320754716981, -2.358490566037736, -3.830188679245283, 0.16981132075471717, -3.0, -2.849056603773585, 2.3584905660377355, 3.0754716981132066, -3.830188679245283, -2.2452830188679247, -0.5094339622641515, 0.283018867924528, 3.7924528301886795, 1.0754716981132075, 2.433962264150943, 2.283018867924528, -2.2830188679245285, -2.6226415094339623, -2.660377358490566, -4.584905660377358, -3.6603773584905666, -0.41509433962264275, -2.415094339622642, -9.320754716981131
            1.830188679245283, 4.381132075471698, 2.0264150943396224, 1.3018867924528301, 1.9169811320754717, 1.8679245283018868, 1.520754716981132, 1.9584905660377359, 3.037735849056604, -4.320754716981132, 0.3207547169811322, 0.16981132075471717, -2.509433962264151, 1.566037735849056, 1.4905660377358485, -2.7358490566037736, 4.584905660377357, 3.679245283018867, -1.9811320754716983, 3.0, -4.132075471698113, 0.283018867924528, 2.4716981132075473, 3.679245283018867, -0.5471698113207548, -3.6415094339622645, -3.981132075471698, -4.849056603773585, -0.7358490566037741, -4.773584905660377, -2.7924528301886795, -9.88679245283019, -1.584905660377359, -7.886792452830189
        };
        StructuredANN ann = ANNBuilder.build(data);
        ANNTrackerController tracker = new ANNTrackerController(ann);
        World w = tracker.getWorld();
//        while (in.next().length() > 0) {
        while (true) {
            tracker.printWorld();
            Thread.sleep(300);
            w.update();
        }
    }

    private void printWorld() {
        //Printing world
        System.out.println("Printing");
        for (int i = world.getHeight() - 1; i >= 1; i--) {
            if (i == world.getObjectHeight()) {
                System.out.println(boolToString(world.getObjectRow()));
            } else {
                System.out.println("");
            }
        }
        //Printing track
        System.out.println(boolToString(world.getTrackerRow()));
    }

    private String boolToString(boolean[] b) {
        StringBuilder sb = new StringBuilder();
        for (boolean bb : b) {
            if (bb) {
                sb.append("X");
            } else {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
