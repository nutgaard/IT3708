/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.onemax;

/**
 *
 * @author Nicklas
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Dimension;
import javax.swing.JFrame;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.PopulationParser;
import no.utgdev.ga.core.population.parser.AverageFitness;
import no.utgdev.ga.core.population.parser.BestFitness;
import no.utgdev.ga.core.population.parser.StandardDeviationFitness;
import no.utgdev.ga.core.statistics.StatisticsHandler;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Nicklas
 */
public class Plotting extends StatisticsHandler {
    private JFrame frame;
    private ChartPanel panel;
    private XYSeriesCollection dataset;
    private JFreeChart chart;
    private XYPlot plot;
    private PopulationParser[] parsers;
    private XYSeries[] series;

    public Plotting(GALoop ga) {
        super(ga);
        frame = new JFrame("Plotting");
        frame.setPreferredSize(new Dimension(800, 800));
        this.dataset = new XYSeriesCollection();
        this.chart = ChartFactory.createXYLineChart(
        "GA", 
        "Generations", 
        "Fitness", 
        dataset, 
        PlotOrientation.VERTICAL, 
        true, 
        true, 
        false);
        this.plot = (XYPlot)chart.getPlot();
        panel = new ChartPanel(chart);
        parsers = new PopulationParser[]{new AverageFitness(), new StandardDeviationFitness(), new BestFitness()};
        this.series = new XYSeries[parsers.length];
        for (int i = 0; i < parsers.length; i++){
            this.series[i] = new XYSeries(parsers[i].getClass().getSimpleName());
            dataset.addSeries(this.series[i]);
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        
    }

    public void generation(int genNo, Population population, FitnessHandler fitnessHandler) {
        for (int i = 0; i < parsers.length; i++) {
            series[i].add(genNo, (Double)parsers[i].parse(population, fitnessHandler));
        }
    }
}
