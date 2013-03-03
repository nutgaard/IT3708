/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.colonelblotto;

import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.PopulationGenerator;
import no.utgdev.ga.utils.TypedProperties;

/**
 *
 * @author Nicklas
 */
public class ColonelBlottoPopulationCreator extends PopulationGenerator<ColonelBlottoPhenoType> {

    public ColonelBlottoPopulationCreator(GALoop ga) {
        super(ga);
    }

    public Population<ColonelBlottoPhenoType> create(int count) {
        TypedProperties props = new TypedProperties(ga.getProperties());
        int B = props.getInt("colonel_blotto.B", 5);
        int S = props.getInt("colonel_blotto.S", 20);
        int bitlength = (int) (B * Math.ceil(Math.log(S) / Math.log(2)));

        ColonelBlottoPhenoType[] list = new ColonelBlottoPhenoType[count];
        for (int i = 0; i < count; i++) {
            list[i] = createPhenoType(bitlength, B);
        }
        return new Population<ColonelBlottoPhenoType>(list);
    }

    private ColonelBlottoPhenoType createPhenoType(int bitlength, int B) {
        boolean[] b = new boolean[bitlength];
        return new ColonelBlottoGenoType(b, B).develop();
    }
}
