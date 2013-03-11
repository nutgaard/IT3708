/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ann.core.node;

import java.util.LinkedList;
import java.util.List;
import no.utgdev.ann.core.ANN;
import no.utgdev.ann.core.node.filter.NodeConnectionFilter;

/**
 *
 * @author Nicklas
 */
public class NodeBasedANN implements ANN {

    public enum LayerType {

        INPUT, HIDDEN, OUTPUT, STATIC;
    }
    private Layer inputLayer;
    private List<Layer> hiddenLayers;
    private List<Layer> staticLayers;
    private Layer outputLayer;

    public NodeBasedANN() {
        this.hiddenLayers = new LinkedList<Layer>();
        this.staticLayers = new LinkedList<Layer>();
    }

    public void addLayer(Layer layer, LayerType type) {
        switch (type) {
            case INPUT:
                this.inputLayer = layer;
                break;
            case HIDDEN:
                this.hiddenLayers.add(layer);
                break;
            case OUTPUT:
                this.outputLayer = layer;
                break;
            case STATIC:
                this.staticLayers.add(layer);
                break;
            default:
                throw new RuntimeException("Unrecognized layertype <"+type.name()+">");
        }
    }
    public void addSynapses(NodeConnectionFilter filter, Layer from, Layer to) {
        from.addSynapses(filter, to);
    }
    public void addSynapses(NodeConnectionFilter filter, LayerType fromType, LayerType toType) {
        Layer[] from = getArrayFromType(fromType);
        Layer[] to = getArrayFromType(toType);
        
        for (Layer lFrom : from) {
            for (Layer lTo : to) {
                lFrom.addSynapses(filter, lTo);
            }
        }
    }
    protected Layer[] getArrayFromType(LayerType type) {
        Layer[] layer;
        
        switch (type) {
            case INPUT:
                layer = new Layer[]{inputLayer};
                break;
            case HIDDEN:
                layer = new Layer[hiddenLayers.size()];
                layer = hiddenLayers.toArray(layer);
                break;
            case OUTPUT:
                layer = new Layer[]{outputLayer};
                break;
            case STATIC:
                layer = new Layer[staticLayers.size()];
                layer = staticLayers.toArray(layer);
                break;
            default:
                throw new RuntimeException("Unrecognized layertype <"+type.name()+">");
        }
        return layer;
    }
}
