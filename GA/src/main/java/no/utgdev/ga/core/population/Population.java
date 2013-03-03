/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.parser.RankFinder;
import org.javatuples.Pair;

/**
 *
 * @author Nicklas
 */
public class Population<T extends PhenoType> implements Iterable<T> {

//    private List<T> population;
    private T[] population;

    public Population(List<T> population) {
//        this.population = population;
        this.population = (T[]) Array.newInstance(PhenoType.class, population.size());
//        this.population = Arrays.copyOf(this.population, population.size());
        this.population = population.toArray(this.population);
    }

    public Population(T[] population) {
//        this.population = Arrays.asList(population);
        this.population = population;
    }

    public T get(int index) {
//        return population.get(index);
        return population[index];
    }

    public Pair<Population<T>, FitnessMap<T>> best(int nof, FitnessHandler fitnessHandler) {
        List<T> best = new LinkedList<T>();
        FitnessMap<T> fitness = fitnessHandler.generateFitnessMap(this);
        int counter = 0;
        for (Entry<Double, List<T>> e : fitness.entrySet()) {
            for (T t : e.getValue()) {
                if (counter >= nof) {
                    break;
                }
                best.add(t);
                counter++;
            }
            if (counter >= nof) {
                break;
            }
        }
        return new Pair<Population<T>, FitnessMap<T>>(new Population<T>(best), fitness);
    }

    public Population merge(Population<T> population) {
//        List<T> m = new LinkedList<T>();
//        m.addAll(this.population);
//        m.addAll(population.population);
        T[] m = Arrays.copyOf(this.population, this.population.length+ population.population.length);
        System.arraycopy(this.population, 0, m, 0, this.population.length);
        System.arraycopy(population.population, 0, m, this.population.length, population.population.length);
        return new Population(m);
    }

    public Population subset(int size) {
//        List<T> copy = new ArrayList<T>(population);
        List<T> copy = new ArrayList<T>(Arrays.asList(population));
        Collections.shuffle(copy);
        return new Population(copy.subList(0, size));
    }

    public int size() {
//        return this.population.size();
        return this.population.length;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int i = 0;
            public boolean hasNext() {
                return i < size();
            }

            public T next() {
                return population[i++];
            }

            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }
}
