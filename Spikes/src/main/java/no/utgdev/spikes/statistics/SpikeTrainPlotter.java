/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.spikes.statistics;

import no.utgdev.spikes.spiketrain.RawSpikeTrain;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Nicklas
 */
public class SpikeTrainPlotter {//extends JFrame {

    private ChartPanel panel;
    private XYSeriesCollection dataset;
    private JFreeChart chart;
    private XYPlot plot;

    public SpikeTrainPlotter(RawSpikeTrain... trains) {
//        super("graph");
//        super.setPreferredSize(new Dimension(800, 800));
        this.dataset = new XYSeriesCollection();
        this.chart = ChartFactory.createXYLineChart(
                "SpikeTrains",
                "Time",
                "Activation level",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        this.plot = (XYPlot) chart.getPlot();
        panel = new ChartPanel(chart);
//        this.series = new XYSeries[trains.length];
        for (int i = 0; i < trains.length; i++) {
//            this.series[i] = createXYSeries(trains[i]);
            dataset.addSeries(createXYSeries(trains[i]));
        }

//        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        super.add(panel);
//        super.pack();
//        super.setVisible(true);
    }

    private XYSeries createXYSeries(RawSpikeTrain rawSpikeTrain) {
        XYSeries series = new XYSeries(Integer.toHexString(rawSpikeTrain.hashCode()));
        int x = 0;
        for (Double d : rawSpikeTrain.getRaw()) {
            series.add(x++, d);
        }
        return series;
    }

    public synchronized void setSeries(RawSpikeTrain... raws) {
        for (int i = 0; i < raws.length; i++) {
            dataset.addSeries(createXYSeries(raws[i]));
        }
        while (dataset.getSeriesCount() > raws.length){
            dataset.removeSeries(0);
        }
    }
    public void save(String filename, String subtitle) {
        try {
            chart.addSubtitle(new TextTitle(subtitle));
            File chartF = new File(filename);
            ChartUtilities.saveChartAsPNG(chartF, chart, 1920, 1080);
            File file = new File(chartF.getParentFile(), "config.attr");
            FileWriter fw = new FileWriter(file);
            fw.append(subtitle);
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(SpikeTrainPlotter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
