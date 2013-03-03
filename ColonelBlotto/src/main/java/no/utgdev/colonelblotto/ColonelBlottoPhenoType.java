/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.colonelblotto;

import java.util.Arrays;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.utils.TypedProperties;

/**
 *
 * @author Nicklas
 */
public class ColonelBlottoPhenoType extends PhenoType<ColonelBlottoGenoType> {
    private int[] troops;

    public ColonelBlottoPhenoType(ColonelBlottoGenoType genoType) {
        super(genoType);
        int battles = genoType.getBattleNo();
        int singleRep = genoType.getVector().length/battles;
        this.troops = new int[battles];
        this.troops = Utils.translate(genoType.getVector(), singleRep);
    }
    public int[] getTroops() {
        return troops;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ColonelBlottoPhenoType{");
        sb.append("vector=");
        sb.append(Arrays.toString(troops));
        sb.append("}");
        return sb.toString();
    }
}
