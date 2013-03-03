/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes;

/**
 *
 * @author Nicklas
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
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
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Nicklas
 */
public class Plotting extends JFrame implements StatisticsHandler {
    List<Pair<PopulationParser, XYSeries>> activeParsers;
    private ChartPanel panel;
    private XYSeriesCollection dataset;
    private JFreeChart chart;
    private XYPlot plot;
    private PopulationParser[] parsers;
    private XYSeries[] series;

    public Plotting() {
        super("graph");
        super.setPreferredSize(new Dimension(800, 800));
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
        parsers = new PopulationParser[]{new AverageFitness(), new StandardDeviationFitness(), new BestFitness(), new Entropy()};
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
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.add(panel);
        super.pack();
        super.setVisible(true);
    }

    public void generation(int genNo, Population population, FitnessHandler fitnessHandler) {
        for (int i = 0; i < parsers.length; i++) {
            series[i].add(genNo, (Double) parsers[i].parse(population, fitnessHandler));
        }
    }
}
