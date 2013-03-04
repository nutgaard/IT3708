/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes;

import java.util.Arrays;

/**
 *
 * @author Nicklas
 */
public class Case implements Comparable<Case> {

        static String[] app = {"000", "00", "0", ""};
        String fitness;
        String[] input;

        public Case(String fitness, String[] input) {
            this.fitness = (fitness.equals("0")) ? "10000" : fitness;
            if (this.fitness.length() < 4) {
                this.fitness = this.fitness + app[this.fitness.length() - 1];
            }
            this.input = input;
        }

        public int compareTo(Case o) {
            int parseMe = Integer.parseInt(this.fitness);
            int parseYou = Integer.parseInt(o.fitness);
            return parseYou - parseMe;

        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("fitness: ").append(fitness).append(" Data: ").append(Arrays.toString(input));
            return sb.toString();
        }
    }
