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
public class BraitenburgAvoidanceRandomness implements Behaviour {

    private int moveCounter = 0;
    private static final int moveCounterLimit = 20;
    private double left = Math.random(), right = Math.random();

    @Override
    public boolean trigger(SensoryInputs input, ResponseDevices devices) {
        return true;
    }

    @Override
    public void execute(SensoryInputs input, ResponseDevices devices) {
        double total = 0;
        if (++moveCounter == moveCounterLimit) {
            moveCounter = 0;
            left =  Math.random();
            right = Math.random();
//            devices.getWheels().moveWheels(Math.random(), Math.random());
        }
        if (input.getProximityArray().getBiggestValueAndIndex().t > 200) {
            double[] d = input.getProximityArray().getValues();

            double sum1 = d[0] + d[1];// + d[2] + d[3];
            double sum2 = d[6] + d[7];// + d[4] + d[5];
            if (d[0] > 500 && d[0] > 500 && d[6] > 500 && d[7] > 500) {
                sum1 *= -100;
                sum2 *= -10;
            }
            total = Math.abs(sum1) + Math.abs(sum2);
            left += sum2 / total;
            right += sum1 / total;
        }
        total = left + right;
        left /= total;
        right /= total;
        devices.getWheels().moveWheels(left, right);
//        devices.getWheels().setWheelSpeed(sum2 / total, sum1 / total);

    }
}
