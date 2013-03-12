/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ctrnngame;

import java.util.Arrays;
import java.util.Scanner;
import no.utgdev.ann.core.structured.StructuredANN;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.Population;
import no.utgdev.trackergame.TrackController;
import no.utgdev.trackergame.World;

/**
 *
 * @author Nicklas
 */
public class ANNTrackerController implements TrackController {

    private World world;
    private StructuredANN ann;
    private double score = 0;
    private int objectCount = 0;

    public ANNTrackerController(StructuredANN ann) {
        this.ann = ann;
        world = new World(30, 15, 5, this);
    }

    public double run(int NOF_objects) {
        while (objectCount < NOF_objects) {
            world.update();
        }
        return score;
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
            move =  0;
        } else if (diff <= 0.33333) {
            move = (int) (sign * 1);
        } else if (diff <= 0.55555) {
            move = (int) (sign * 2);
            score += 0.01;
        } else if (diff <= 0.77777) {
            move = (int) (sign * 3);
            score += 0.01;
        } else {
            move = (int) (sign * 4);
            score += 0.01;
        }
//        System.out.println("Output: "+Arrays.toString(output)+" diff: "+diff+" sign: "+sign);
//        System.out.println("Move: "+move);
        return move;
    }

    public void objectDone(double overlap, int objectSize) {
        objectCount++;
        if (objectSize < 5) {
            //Should catch them
            if (overlap == 1.0) {
                score += 2;
            } else {
                score -= 1;
            }
        } else {
            //Avoid them
            if (overlap == 0.0) {
                score += 2;
            } else {
                score -= 1;
            }
        }
    }

    public World getWorld() {
        return this.world;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double[] data = new double[]{
            3.8075471698113206, 1.0754716981132075, 4.818867924528302, 2.7660377358490567, 1.6830188679245284, 1.0981132075471698, 1.0150943396226415, 1.3283018867924528, 4.09433962264151, 2.584905660377358, 4.283018867924527, -2.0943396226415096, -4.886792452830188, 1.3773584905660377, -1.150943396226415, -2.358490566037736, -1.6415094339622645, 2.1698113207547163, 2.735849056603773, 2.962264150943396, -0.9622641509433967, 2.3207547169811322, -4.283018867924528, 0.6981132075471699, -4.622641509433962, 3.0754716981132066, 2.849056603773585, 0.09433962264150964, 3.452830188679245, -0.09433962264150964, -3.283018867924529, -1.3962264150943398, -2.6792452830188678, -3.169811320754717
        };
        StructuredANN ann = ANNBuilder.build(data);
        ANNTrackerController tracker = new ANNTrackerController(ann);
        World w = tracker.getWorld();
        while (in.next().length() > 0) {
            tracker.printWorld();
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
