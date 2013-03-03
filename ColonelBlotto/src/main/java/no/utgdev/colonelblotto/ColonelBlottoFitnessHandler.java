/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.colonelblotto;

import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.utils.TypedProperties;
import org.javatuples.Pair;

/**
 *
 * @author Nicklas
 */
public class ColonelBlottoFitnessHandler extends FitnessHandler<ColonelBlottoGenoType, ColonelBlottoPhenoType> {
    
    private static double rf, lf;
    private static int S, B;

    private Pair<Population<ColonelBlottoPhenoType>, FitnessMap<ColonelBlottoPhenoType>> last;

    public ColonelBlottoFitnessHandler(GALoop ga) {
        super(ga);
        TypedProperties typed = new TypedProperties(ga.getProperties());
        rf = typed.getDouble("colonel_blotto.rf", 1.0);
        lf = typed.getDouble("colonel_blotto.lf", 0.05);
        S = typed.getInt("colonel_blotto.S", 20);
        B = typed.getInt("colonel_blotto.B", 5);
    }

    
    
    public FitnessMap<ColonelBlottoPhenoType> generateFitnessMap(Population<ColonelBlottoPhenoType> population) {
        if (last != null && last.getValue0() == population) {
            return last.getValue1();
        }
        FitnessMap<ColonelBlottoPhenoType> map = new FitnessMap<ColonelBlottoPhenoType>();
        for (ColonelBlottoPhenoType cbpt : population) {
            map.put(cbpt, getFitness(cbpt, population));
        }
        last = new Pair(population, map);
        return map;
    }

    public double getFitness(ColonelBlottoPhenoType me, Population<ColonelBlottoPhenoType> population) {
        int[] myTroops = me.getTroops();
        int troops = 0;
        for (int i = 0; i < myTroops.length; i++) {
            troops += myTroops[i];
        }
        if (troops > S) {
            return 0;
        }
        int win = 0;
        for (ColonelBlottoPhenoType other : population) {
            if (other == me) {
                continue;
            }
            int[] hisTroops = other.getTroops();

            double my_sf = 1.0, his_sf = 1.0;

            for (int battleNo = 0; battleNo < myTroops.length; battleNo++) {
                if (my_sf * myTroops[battleNo] > his_sf * hisTroops[battleNo]) {
                    int r = (int) (rf * (myTroops[battleNo] - hisTroops[battleNo]) / (myTroops.length - battleNo));
                    for (int rest = battleNo + 1; rest < myTroops.length; rest++) {
                        myTroops[rest] += r;
                    }
                    his_sf -= lf;
                    win += 2;
                } else if (my_sf * myTroops[battleNo] < his_sf * hisTroops[battleNo]) {
                    int r = (int) (rf * (hisTroops[battleNo] - myTroops[battleNo]) / (hisTroops.length - battleNo));
                    for (int rest = battleNo + 1; rest < hisTroops.length; rest++) {
                        hisTroops[rest] += r;
                    }
                    my_sf -= lf;
                } else {
                    win++;
                }
            }
        }
        return win;
    }
}
