/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package device;

import device.input.LightArray;
import device.input.ProximityArray;

/**
 *
 * @author Nicklas
 */
public class SensoryInputs {
    private ProximityArray proximity;
    private LightArray light;

    public SensoryInputs(ProximityArray proximity, LightArray light) {
        this.proximity = proximity;
        this.light = light;
    }

    public ProximityArray getProximityArray() {
        return proximity;
    }

    public LightArray getLightArray() {
        return light;
    }
}
