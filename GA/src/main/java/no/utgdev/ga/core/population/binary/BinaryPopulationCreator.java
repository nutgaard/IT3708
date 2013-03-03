/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core.population.binary;

import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.PopulationGenerator;
import no.utgdev.ga.utils.TypedProperties;

/**
 *
 * @author Nicklas
 */
public class BinaryPopulationCreator extends PopulationGenerator<BinaryPhenoType> {

    public BinaryPopulationCreator(GALoop ga) {
        super(ga);
    }
    
    public Population<BinaryPhenoType> create(int count) {
        TypedProperties props = new TypedProperties(ga.getProperties());
        String target = props.getString("binary.target", null);
        int bitlength;
        if (target == null) {
            bitlength = props.getInt("binary.length", 8);
        } else {
            bitlength = target.length();
        }
        BinaryPhenoType[] list = new BinaryPhenoType[count];
        
        for (int i = 0; i < count; i++) {
            list[i] = createPhenoType(bitlength);
        }
        return new Population<BinaryPhenoType>(list);
    }

    private BinaryPhenoType createPhenoType(int bitlength) {
        boolean[] vector = new boolean[bitlength];
        for (int i = 0; i < bitlength; i++) {
//            vector[i] = (Math.random() > 0.5) ? true : false; 
        }
        return new BinaryPhenoType(new BinaryGenoType(vector));
    }
}
