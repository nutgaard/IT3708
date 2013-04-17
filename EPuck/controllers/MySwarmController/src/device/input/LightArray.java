/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package device.input;

import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;
import com.cyberbotics.webots.controller.Robot;
import utils.Pair;

/**
 *
 * @author Nicklas
 */
public class LightArray {
    private LightSensor[] lights;

    public LightArray(Robot robot, int numberOfSenors) {
        this.lights = new LightSensor[numberOfSenors];
        for (int i = 0; i < numberOfSenors; i++) {
            this.lights[i] = robot.getLightSensor("ls"+i);
            this.lights[i].enable(64);
        }
    }
    public LightSensor getLightSensor(int id) {
        return this.lights[id];
    }
    public double getLightSensorValue(int id) {
        return getLightSensor(id).getValue();
    }
    public Pair<Integer, Double> getSmallestValueAndIndex() {
        double smallest = getLightSensorValue(0);
        int index = 0;
        for (LightSensor ds : lights) {
            if (ds.getValue() < smallest) {
                smallest = ds.getValue();
                
            }
        }
        return new Pair<>(index, smallest);
    }
    public Pair<Integer, Double> getBiggestValueAndIndex() {
        double biggest = getLightSensorValue(0);
        int index = 0;
        for (LightSensor ds : lights) {
            if (ds.getValue() > biggest) {
                biggest = ds.getValue();
            }
        }
        return new Pair<>(index, biggest);
    }
    public double[] getValues() {
        double[] o = new double[lights.length];
        int i = 0;
        for (LightSensor ds : lights){
            o[i++] = ds.getValue();
        }
        return o;
    }
}
