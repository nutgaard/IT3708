/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brooks.impl;

import brooks.Behaviour;
import device.ResponseDevices;
import device.SensoryInputs;

/**
 *
 * @author Nicklas
 */
public class BraitenburgAvoidance implements Behaviour {
    @Override
    public boolean trigger(SensoryInputs input, ResponseDevices devices) {
        return true;
    }

    @Override
    public void execute(SensoryInputs input, ResponseDevices devices) {
        
        double[] d = input.getProximityArray().getValues();
        double sum1 = d[0] + d[1] + d[2] + d[3];
        double sum2 = d[4] + d[5] + d[6] + d[7];
        double total = sum1 + sum2;
        
//        devices.getWheels().setWheelSpeed(sum2 / total, sum1 / total);
        devices.getWheels().moveWheels(sum2/total, sum1/total);
    }
}
