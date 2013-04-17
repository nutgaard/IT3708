/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package device.input;

import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.Robot;
import utils.Pair;

/**
 *
 * @author Nicklas
 */
public class ProximityArray {

    private DistanceSensor[] distances;

    public ProximityArray(Robot robot, int numberOfSenors) {
        this.distances = new DistanceSensor[numberOfSenors];
        for (int i = 0; i < numberOfSenors; i++) {
            this.distances[i] = robot.getDistanceSensor("ps"+i);
            this.distances[i].enable(64);
        }
    }
    public DistanceSensor getDistanceSensor(int id) {
        return this.distances[id];
    }
    public double getDistanceSensorValue(int id) {
        return getDistanceSensor(id).getValue();
    }
    public Pair<Integer, Double> getSmallestValueAndIndex() {
        double smallest = getDistanceSensorValue(0);
        int index = 0;
        for (DistanceSensor ds : distances) {
            if (ds.getValue() < smallest) {
                smallest = ds.getValue();
                
            }
        }
        return new Pair<>(index, smallest);
    }
    public Pair<Integer, Double> getBiggestValueAndIndex() {
        double biggest = getDistanceSensorValue(0);
        int index = 0;
        for (DistanceSensor ds : distances) {
            if (ds.getValue() > biggest) {
                biggest = ds.getValue();
            }
        }
        return new Pair<>(index, biggest);
    }
    public double[] getValues() {
        double[] o = new double[distances.length];
        int i = 0;
        for (DistanceSensor ds : distances){
            o[i++] = ds.getValue();
        }
        return o;
    }
}
