/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ctrnngame;

import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.PopulationGenerator;

/**
 *
 * @author Nicklas
 */
public class CTRNNPopulationCreator extends PopulationGenerator<CTRNNPhenoType> {

    public CTRNNPopulationCreator(GALoop ga) {
        super(ga);
    }

    @Override
    public Population<CTRNNPhenoType> create(int count) {
        int totalBit = 272;
        CTRNNPhenoType[] list = new CTRNNPhenoType[count];
        for (int i = 0; i < count; i++) {
            list[i] = createPhenoType(totalBit);
        }
        return new Population<CTRNNPhenoType>(list);
    }
    private CTRNNPhenoType createPhenoType(int bitsize) {
        boolean[] b = new boolean[bitsize];
        for (int i = 0; i < bitsize; i++) {
            if (Math.random() > 0.5) {
                b[i] = true;
            }
        }
        return new CTRNNGenoType(b).develop();
    }
    
}
