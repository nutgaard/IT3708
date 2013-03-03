/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.fitness;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import no.utgdev.ga.core.population.PhenoType;

/**
 *
 * @author Nicklas
 */
public class FitnessMap<T extends PhenoType> extends TreeMap<Double, List<T>> {

    public FitnessMap(SortedMap<Double, ? extends List<T>> m) {
        super(getComp());
        super.putAll(m);
    }

    public FitnessMap() {
        super(getComp());
    }
    private static Comparator<Double> getComp(){
        return new Comparator<Double>() {

            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        };
    }
    public void put(T key, Double value) {
        List<T> l;
        if (super.containsKey(value)) {
            l = super.get(value);
        } else {
            l = new LinkedList<T>();
            super.put(value, l);
        }
        l.add(key);
    }

    public Double get(T key) {
        for (Entry<Double, List<T>> entry : entrySet()) {
            if (entry.getValue().contains(key)) {
                return entry.getKey();
            }
        }
        return null;
    }
    @Override
    public int size() {
        int c = 0;
        for (Entry<Double, List<T>> entry : entrySet()){
            c += entry.getValue().size();
        }
        return c;
    }
}
