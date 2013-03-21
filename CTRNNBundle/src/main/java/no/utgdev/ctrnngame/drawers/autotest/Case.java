/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ctrnngame.drawers.autotest;

import java.util.Arrays;

/**
 *
 * @author Nicklas
 */
public class Case implements Comparable<Case> {

        String fitness;
        String[] input;

        public Case(String fitness, String[] input) {
            this.fitness = fitness;
            this.input = input;
        }

        public int compareTo(Case o) {
            double parseMe = Double.parseDouble(this.fitness);
            double parseYou = Double.parseDouble(o.fitness);
            return (int)Math.signum(parseYou - parseMe);

        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("fitness: ").append(fitness).append(" Data: ").append(Arrays.toString(input));
            return sb.toString();
        }
    }
