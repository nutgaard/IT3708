/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ctrnngame;

/**
 *
 * @author Nicklas
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import no.utgdev.ga.core.GALoop;
import no.utgdev.ga.core.fitness.FitnessHandler;
import no.utgdev.ga.core.population.Population;
import no.utgdev.ga.core.population.PopulationParser;
import no.utgdev.ga.core.population.parser.AverageFitness;
import no.utgdev.ga.core.population.parser.BestFitness;
import no.utgdev.ga.core.population.parser.StandardDeviationFitness;
import no.utgdev.ga.core.statistics.StatisticsHandler;
import org.javatuples.Pair;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Nicklas
 */
public class Plotting extends StatisticsHandler {
    List<Pair<PopulationParser, XYSeries>> activeParsers;
//    private JFrame frame;
    private ChartPanel panel;
    private XYSeriesCollection dataset;
    private JFreeChart chart;
    private XYPlot plot;
    private PopulationParser[] parsers;
    private XYSeries[] series;

    public Plotting(GALoop ga) {
        super(ga);
//        frame = new JFrame("Plotting");
//        frame.setPreferredSize(new Dimension(800, 800));
        this.activeParsers = new LinkedList<Pair<PopulationParser, XYSeries>>();
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
        this.plot = (XYPlot) chart.getPlot();
        panel = new ChartPanel(chart);
        parsers = new PopulationParser[]{new AverageFitness(), new StandardDeviationFitness(), new BestFitness()};
        this.series = new XYSeries[parsers.length];
        for (int i = 0; i < parsers.length; i++) {
            this.series[i] = new XYSeries(parsers[i].getClass().getSimpleName());
            if (parsers[i].getClass().getName().contains("Entropy")) {
                NumberAxis axis2 = new NumberAxis("Entropy");
                plot.setRangeAxis(1, axis2);
                plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
                XYSeriesCollection c = new XYSeriesCollection();
                c.addSeries(series[i]);
                plot.setDataset(1, c);
                plot.mapDatasetToRangeAxis(1, 1);
                plot.setRenderer(1, new StandardXYItemRenderer(2));
            } else {
                dataset.addSeries(this.series[i]);
            }
        }
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(panel);
//        frame.pack();
//        frame.setLocationByPlatform(true);
//        frame.setVisible(true);
    }

    public void generation(int genNo, Population population, FitnessHandler fitnessHandler) {
        for (int i = 0; i < parsers.length; i++) {
            series[i].add(genNo, (Double) parsers[i].parse(population, fitnessHandler));
        }
    }
    public void save(String filename, String subtitle) {
        try {
            chart.addSubtitle(new TextTitle(subtitle));
            ChartUtilities.saveChartAsPNG(new File(filename), chart, 1920, 1080);
        } catch (IOException ex) {
            Logger.getLogger(Plotting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
