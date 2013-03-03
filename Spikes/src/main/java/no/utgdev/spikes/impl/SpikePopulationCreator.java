/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes.impl;

import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.PopulationGenerator;
import no.utgdev.ga.utils.TypedProperties;

/**
 *
 * @author Nicklas
 */
public class SpikePopulationCreator extends PopulationGenerator<SpikePhenoType> {

    public SpikePopulationCreator(GALoop ga) {
        super(ga);
    }
    
    

    public Population<SpikePhenoType> create(int count) {
        TypedProperties props = new TypedProperties(ga.getProperties());
        int bitPerParam = props.getInt("spike.param.accuracy", 8);
        int totalBit = bitPerParam*5;
        SpikePhenoType[] list = new SpikePhenoType[count];
        
        for (int i = 0; i < count; i++) {
            list[i] = createPhenoType(totalBit);
        }
        return new Population<SpikePhenoType>(list);
    }
    private SpikePhenoType createPhenoType(int bitsize) {
        boolean[] b = new boolean[bitsize];
        for (int i = 0; i < bitsize; i++){
            if (Math.random() > 0.5){
                b[i] = true;
            }
        }
        return new SpikeGenoType(b).develop();
    }
    
}
