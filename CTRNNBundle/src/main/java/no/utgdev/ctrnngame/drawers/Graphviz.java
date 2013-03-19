package no.utgdev.ctrnngame.drawers;


import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import no.utgdev.ann.core.structured.AbstractNeuron;
import no.utgdev.ann.core.structured.NeuralLayer;
import no.utgdev.ann.core.structured.StructuredANN;
import no.utgdev.ann.core.structured.Synapse;
import org.jgraph.graph.DefaultEdge;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.ListenableDirectedGraph;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Nicklas
 */
public class Graphviz {

    private boolean graphCreated = false;
    private static Map<String, String> colorMap = new HashMap<String, String>();
    private static String[] attrColors = new String[]{"aquamarine2", "cadetblue3", "deepskyblue3", "darkgreen", "cornflowerblue", "yellowgreen", "yellow4"};
    private static String[] clsColors = new String[]{"Olivedrab2", "LightPink"};
    
    static {
        attrColors = new String[]{"lemonchiffon3"};
    }
     
    
    private static int colorsUsed;

    public Graphviz() {
    }

    public void createGraph(StructuredANN ann, String filename) throws Exception {
        FileWriter fw = new FileWriter(new File(filename+".dot"));
        fw.append("digraph G{\n");
        StringBuilder structure = new StringBuilder();
        StringBuilder connections = new StringBuilder();
        
        Map<AbstractNeuron, String> neuronNames = new HashMap<AbstractNeuron, String>();
        List<AbstractNeuron> neuronList = new LinkedList<AbstractNeuron>();
        int intName = 1;
        String neuronName = null;

        NeuralLayer[] layers = ann.getLayers();
        int layerCount = 0;
        for (NeuralLayer nl : layers) {
            structure.append("subgraph cluster_").append(layerCount++).append("{\n");
            List<? extends AbstractNeuron> neurons = nl.getNeurons();
            for (AbstractNeuron neuron : neurons) {
                neuronName = String.valueOf(intName++);
                neuronList.add(neuron);
                neuronNames.put(neuron, neuronName);
                structure.append(neuronName).append(";\n");
            }
            structure.append("}\n");
        }
        String idString;
        for (AbstractNeuron an : neuronList) {
            idString = neuronNames.get(an);
        }
        String neuronNameFrom, neuronNameTo;
        DecimalFormat formatter = new DecimalFormat("##.###");
        for (AbstractNeuron an : neuronList) {
            neuronNameTo = neuronNames.get(an);
            for (Synapse s : an.getInputSynapses()) {
                neuronNameFrom = neuronNames.get(s.getFrom());
                connections
                        .append(neuronNameFrom)
                        .append(" -> ")
                        .append(neuronNameTo)
                        .append("[ label=\"")
                        .append(formatter.format(s.getWeight()))
                        .append("\"];\n");
            }
        }
        fw.append(structure).append("\n");
        fw.append(connections).append("\n");
//        fw.append(" labelloc=\"t\"\n");
//        fw.append(" label=\"" + String.format("Classification accuracy: %1$.2f%2$s", accuracy * 100, "%") + "\"\n");
        fw.append("}");
        fw.close();
        this.graphCreated = true;
    }

    public void show() throws Exception {
        Process p = Runtime.getRuntime().exec("cmd.exe /c makeGraph.bat");
        p.waitFor();
    }

    private static String attrString(String l) {
        return "[shape=\"box\", style=\"filled\" color=\"black\", fillcolor=\"" + attrColor(l) + "\", label=\"" + l + "\"]";
    }

    private static String clsString(String l) {
        return "[shape=\"box\", style=\"filled\" color=\"black\", fillcolor=\"" + clsColor(l) + "\", label=\"" + l + "\"]";
    }

    private static String attrColor(String nodeName) {
        if (colorMap.containsKey(nodeName)) {
            return colorMap.get(nodeName);
        } else {
            int c = (colorsUsed++ % attrColors.length);
            colorMap.put(nodeName, attrColors[c]);
            return attrColors[c];
        }
    }
    private static String clsColor(String nodeString) {
        return clsColors[nodeString.length()-4];
    }
}
