/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brooks;

import device.ResponseDevices;
import device.SensoryInputs;
import utils.Pair;

/**
 *
 * @author Nicklas
 */
public class TowardsTheLight implements Behaviour {

    public static final double IRThreshold = 3700;

    public TowardsTheLight() {
        
    }

    @Override
    public boolean trigger(SensoryInputs input, ResponseDevices devices) {
        double[] lights = input.getLightArray().getValues();
        int lId = 0;
        for (double l : lights) {
            if (l < IRThreshold) {
                devices.getLedArray().setLEDState(lId, true);
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute(SensoryInputs input, ResponseDevices devices) {
        double[] d = input.getLightArray().getValues();
        double sum1 = d[0] + d[1] + d[2] + d[3];
        double sum2 = d[4] + d[5] + d[6] + d[7];
        double total = sum1 + sum2;
        devices.getWheels().setWheelSpeed(sum2 / total, sum1 / total);
    }
}
