package no.utgdev.spikes;

import java.io.File;
import java.util.List;
import no.utgdev.spikes.impl.SpikePopulationCreator;
import no.utgdev.spikes.impl.SpikePhenoType;
import no.utgdev.spikes.impl.SpikeGenoType;
import no.utgdev.spikes.impl.SpikeFitnessHandler;
import no.utgdev.spikes.statistics.Plotting;
import java.util.Properties;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessMap;
import no.utgdev.ga.core.selection.mechanism.AllMechanism;
import no.utgdev.ga.core.selection.mechanism.SigmaScalingMechanism;
import no.utgdev.ga.core.selection.protocol.AllProtocol;
import no.utgdev.ga.core.selection.protocol.GenerationalMixing;
import no.utgdev.ga.utils.AskingProperties;
import no.utgdev.spikes.spiketrain.RawSpikeTrain;
import no.utgdev.spikes.spiketrain.SpikeTrainFromFile;
import no.utgdev.spikes.spiketrain.distancemetric.SpikeTime;
import no.utgdev.spikes.statistics.SpikeTrainPlotter;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        Properties properties = new Properties();
        //Do not touch
        properties.setProperty("core.population.generator", SpikePopulationCreator.class.getName());
        properties.setProperty("core.fitness.handler", SpikeFitnessHandler.class.getName());
        properties.setProperty("core.statistics.handler", Plotting.class.getName());
        properties.setProperty("core.strategy.adult.mechanism", AllMechanism.class.getName());
        properties.setProperty("core.strategy.parent.protocol", AllProtocol.class.getName());
        properties.setProperty("core.generation.size", "1000");
        properties.setProperty("core.population.size", "200");
        properties.setProperty("spike.length", "1000");
        properties.setProperty("debug.generational_sysout", "1000");
        properties.setProperty("spike.param.accuracy", "30");


        //Touch sometimes
        properties.setProperty("spike.target.uri", "./data/izzy-train1.dat");

        if (args != null && args.length == 9) {
            properties.setProperty("core.strategy.adult.protocol", args[0]);
            properties.setProperty("core.strategy.parent.mechanism", args[1]);
            properties.setProperty("tournament.size", args[2]);
            properties.setProperty("tournament.eps", args[3]);
            properties.setProperty("core.individual.crossover_rate", args[4]);
            properties.setProperty("core.individual.mutation_rate", args[5]);
            properties.setProperty("spike.fitness.metric", args[6]);
            properties.setProperty("core.population.size", args[7]);
            properties.setProperty("spike.target.uri", args[8]);
        } else {
            //Touch
            properties.setProperty("core.strategy.adult.protocol", GenerationalMixing.class.getName());
            properties.setProperty("core.strategy.parent.mechanism", SigmaScalingMechanism.class.getName());
            properties.setProperty("tournament.size", "30");
            properties.setProperty("tournament.eps", "0.05");
            properties.setProperty("core.individual.crossover_rate", "0.9");
            properties.setProperty("core.individual.mutation_rate", "0.4");
            properties.setProperty("spike.fitness.metric", SpikeTime.class.getName());
        }

//        Same for a, b, c, d, k
//        properties.setProperty("spike.a.min", "0.001");
//        properties.setProperty("spike.a.max", "0.2");



        AskingProperties ask = new AskingProperties(properties);
//        properties = ask.ask();
        GALoop ga = new GALoop(properties);
        SpikeGenoType.setParams(ga, properties);
        SpikePhenoType.setParams(ga, properties);

        FitnessMap<SpikePhenoType> fm = (FitnessMap<SpikePhenoType>) ga.run();
        List<SpikePhenoType> best = fm.get(fm.keySet().iterator().next());
        double fitnessBest = fm.get(best.get(0));
        System.out.println("Number of Top: " + best.size() + " Fitness: " + fm.get(best.get(0)));
//        for (PhenoType pt : best) {
//            System.out.println(pt);
//        }
        RawSpikeTrain target = new SpikeTrainFromFile(ga).generate(new File(properties.getProperty("spike.target.uri")));
        String subtitle = subtitle(best.get(0));
        ((Plotting) ga.getStatisticsHandler()).save(fileName(fitnessBest, properties, true), subtitle);
        new SpikeTrainPlotter(target, best.get(0).getSpikeTrain()).save(fileName(fitnessBest, properties, false), subtitle);
    }

    private static String fileName(double prefix, Properties properties, boolean progression) {
        StringBuilder sb = new StringBuilder();
        sb.append("./backup/");
        
        String n = String.valueOf(prefix);
        String dataset = properties.getProperty("spike.target.uri");
        sb.append("izzy");
        if (dataset.contains("1")) {
            sb.append(1).append("/");
            sb.append(n.substring(2, Math.min(6, n.length()))).append("-");
            sb.append("izzy").append(1);
        } else if (dataset.contains("2")) {
            sb.append(2).append("/");
            sb.append(n.substring(2, Math.min(6, n.length()))).append("-");
            sb.append("izzy").append(2);
        } else if (dataset.contains("3")) {
            sb.append(3).append("/");
            sb.append(n.substring(2, Math.min(6, n.length()))).append("-");
            sb.append("izzy").append(3);
        } else if (dataset.contains("4")) {
            sb.append(4).append("/");
            sb.append(n.substring(2, Math.min(6, n.length()))).append("-");
            sb.append("izzy").append(4);
        }
        sb.append("-");
        sb.append(properties.getProperty("core.generation.size")).append("gen-");
        sb.append(properties.getProperty("core.population.size")).append("pop-");
        sb.append(properties.getProperty("tournament.size")).append("tour-");
        sb.append(properties.getProperty("tournament.eps")).append("eps-");
        sb.append(properties.getProperty("core.individual.crossover_rate")).append("cr-");
        sb.append(properties.getProperty("core.individual.mutation_rate")).append("mr-");
        sb.append(properties.getProperty("spike.param.accuracy")).append("acc-");

        String protocol = properties.getProperty("core.strategy.adult.protocol");
        if (protocol.contains("FullGenerationReplacement")) {
            sb.append("GenRep-");
        } else if (protocol.contains("GenerationalMixing")) {
            sb.append("GenMix-");
        } else if (protocol.contains("OverProduction")) {
            sb.append("OverProd-");
        }
        String mechanism = properties.getProperty("core.strategy.parent.mechanism");
        if (mechanism.contains("FitnessProportionateMechanism")) {
            sb.append("Fitness-");
        } else if (mechanism.contains("RankMechanism")) {
            sb.append("Rank-");
        } else if (mechanism.contains("SigmaScalingMechanism")) {
            sb.append("Sigma-");
        } else if (mechanism.contains("TournamentSelectionMechanism")) {
            sb.append("Tourna-");
        }
        String metric = properties.getProperty("spike.fitness.metric");
        if (metric.contains("SpikeInterval")) {
            sb.append("Interval");
        } else if (metric.contains("SpikeTime")) {
            sb.append("Time");
        } else if (metric.contains("Waveform")) {
            sb.append("Wave");
        }
        if (progression) {
            sb.append("-prog");
        }


        sb.append(".png");
        return sb.toString();
    }

    private static String subtitle(SpikePhenoType get) {
        StringBuilder sb = new StringBuilder();
        String[] p = {"a: ", " b: ", " c: ", " d: ", " k: "};
        double[] v = get.getParams();
        for (int i = 0; i < 5; i++) {
            sb.append(p[i]).append(v[i]);
        }
        return sb.toString();
    }
}
