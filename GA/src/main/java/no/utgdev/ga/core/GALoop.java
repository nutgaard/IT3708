/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.core;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.population.GenoType;
import no.utgdev.ga.core.population.PhenoType;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.PopulationGenerator;
import no.utgdev.ga.core.selection.SelectionMechanism;
import no.utgdev.ga.core.selection.SelectionProtocol;
import no.utgdev.ga.core.selection.SelectionStrategy;
import no.utgdev.ga.core.selection.mechanism.NullMechanism;
import no.utgdev.ga.core.selection.protocol.NullProtocol;
import no.utgdev.ga.core.statistics.NullStatistics;
import no.utgdev.ga.core.statistics.StatisticsHandler;
import no.utgdev.ga.utils.TypedProperties;
import org.javatuples.Pair;

/**
 *
 * @author Nicklas
 */
public class GALoop {

    private Properties props;
    private double crossoverRate, mutationRate;
    private StatisticsHandler statisticHandler;

    public GALoop(Properties properties) {
        this.props = properties;
    }

    public Properties getProperties() {
        return props;
    }
    public StatisticsHandler getStatisticsHandler() {
        return this.statisticHandler;
    }
    /**
     * This is the main GA loop
     */
    public void run() {
        try {
            TypedProperties properties = new TypedProperties(props);
            Class<? extends PopulationGenerator> populationGenerator = findClass("core.population.generator", null);
            Class<? extends SelectionProtocol> adultSelectionProtocol = findClass("core.strategy.adult.protocol", NullProtocol.class.getName());
            Class<? extends SelectionMechanism> adultSelectionMechanism = findClass("core.strategy.adult.mechanism", NullMechanism.class.getName());
            Class<? extends SelectionProtocol> parentSelectionProtocol = findClass("core.strategy.parent.protocol", NullProtocol.class.getName());
            Class<? extends SelectionMechanism> parentSelectionMechanism = findClass("core.strategy.parent.mechanism", NullMechanism.class.getName());
            Class<? extends FitnessHandler> fitnessHandlerCls = findClass("core.fitness.handler", null);
            Class<? extends StatisticsHandler> statisticHandlerCls = findClass("core.statistics.handler", NullStatistics.class.getName());

            int NOF_generations = properties.getInt("core.generation.size", 10);
            int populationCount = properties.getInt("core.population.size", 20);
            int generationalSysout = properties.getInt("debug.generational_sysout", 50);
            int generationCounter = 0;
            
            this.statisticHandler = createInstance(statisticHandlerCls);
            Population<PhenoType> childrenPopulation = createInstance(populationGenerator).create(populationCount);
            Population<PhenoType> adultPopulation = new Population(new PhenoType[]{}), matingPopulation = new Population(new PhenoType[]{});
            FitnessHandler fitnessHandler = createInstance(fitnessHandlerCls);
            
            SelectionStrategy adultStrategy = new SelectionStrategy(createInstance(adultSelectionProtocol), createInstance(adultSelectionMechanism));
            SelectionStrategy parentStrategy = new SelectionStrategy(createInstance(parentSelectionProtocol), createInstance(parentSelectionMechanism));
            
            crossoverRate = properties.getDouble("core.individual.crossover_rate", 0.9);
            mutationRate = properties.getDouble("core.individual.mutation_rate", 0.1);
            
            while (generationCounter < NOF_generations) {
                if (generationCounter % generationalSysout == 0) {
                    System.out.println("Generation " + generationCounter);
                }
                
                //Register statistics
                statisticHandler.generation(generationCounter, childrenPopulation, fitnessHandler);
                
                //Adult selection
//                adultPopulation = adultStrategy.getProtocol().selection(adultPopulation, childrenPopulation, fitnessHandler, populationCount);
                adultPopulation = adultStrategy.selection(adultPopulation, childrenPopulation, populationCount, fitnessHandler);
                
                //Parent selection
                matingPopulation = parentStrategy.selection(adultPopulation, new Population(new PhenoType[]{}), populationCount, fitnessHandler);
                
                //Reproduction
                List<PhenoType> children = new LinkedList<PhenoType>();
                for (int i = 0; i < populationCount * parentStrategy.getProtocol().generatationRatio(); i += 2) {
                    Pair<PhenoType, PhenoType> matingPair = parentStrategy.getMechanism().getPhenoTypePair(matingPopulation, fitnessHandler);
                    children.add(reproduce(matingPair.getValue0(), matingPair.getValue1()).develop());
                    children.add(reproduce(matingPair.getValue1(), matingPair.getValue0()).develop());
                }
                childrenPopulation = new Population(children);
                generationCounter++;
            }
            FitnessMap<PhenoType> fm = fitnessHandler.generateFitnessMap(childrenPopulation);
            List<PhenoType> best = fm.get(fm.keySet().iterator().next());
            System.out.println("Number of Top: " + best.size()+" Fitness: "+fm.get(best.get(0)));
            for (PhenoType pt : best) {
                System.out.println(pt);
            }
        } catch (Exception ex) {
            Logger.getLogger(GALoop.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private GenoType reproduce(PhenoType mate1, PhenoType mate2) {
        double r1 = Math.random(), r2 = Math.random();
        GenoType base;
        if (r1 < crossoverRate) {
            base = mate1.getGenoType().crossover(mate2.getGenoType());
        } else {
            base = mate1.getGenoType();
        }
        if (r2 < mutationRate) {
            base = base.mutate();
        }
        return base;
    }

    private Class findClass(String propertyName, String defaultValue) {
        TypedProperties properties = new TypedProperties(props);
        String property = properties.getString(propertyName, defaultValue);
        if (property == null) {
            throw new RuntimeException(propertyName + " must be specified");
        } else {
            try {
                return Class.forName(property);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(propertyName + " could not be found, was looking for: " + property);
            }
        }
    }
    private <T> T createInstance(Class<T> cls) {
        try {
            Constructor<T> constructor = cls.getConstructor(this.getClass());
            T instance = constructor.newInstance(this);
            return instance;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
