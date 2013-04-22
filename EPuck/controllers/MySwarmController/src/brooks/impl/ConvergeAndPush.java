/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brooks.impl;

import brooks.Behaviour;
import device.ResponseDevices;
import device.SensoryInputs;
import device.input.ProximityArray;
import utils.Pair;

/**
 *
 * @author Nicklas
 */
public class ConvergeAndPush implements Behaviour {

    private static final double[] sensorOrientations = {1.27, 0.77, 0.00, 5.21, 4.21, 3.14, 2.37, 1.87};
    private static final double goalOrientation = Math.PI / 2.;
    private static final double IRDetectionThreshold = 3700;
    private static final double IRPushThreshold = 500;
    private static final double orientationDiff = 100;
    public static final int frontLightRealignThreshold = 200;
    public static final int pushBoxAlignmentThreshold = 3*frontLightRealignThreshold;

    @Override
    public boolean trigger(SensoryInputs input, ResponseDevices devices) {
        return input.getLightArray().getSmallestValueAndIndex().t < IRDetectionThreshold;
    }

    @Override
    public void execute(SensoryInputs input, ResponseDevices devices) {
        if (input.getLightArray().getSmallestValueAndIndex().t < IRPushThreshold) {
            pushBox(input, devices);
        } else {
            converageOnBox(input, devices);
        }
    }

    private void converageOnBox(SensoryInputs input, ResponseDevices devices) {
        changeOrientationToBox(input, devices);
        moveForward(input, devices);
    }

    private void changeOrientationToBox(SensoryInputs input, ResponseDevices devices) {
        Pair<Integer, Double> smallestReading = input.getLightArray().getSmallestValueAndIndex();
        if (isCorrectlyOrientet(input, devices)) {
            devices.getWheels().stop();
            return;
        }
        double sensorOrientation = sensorOrientations[smallestReading.s];
        double radiansToTurn = goalOrientation - sensorOrientation;
        double degrees = radiansToDegrees(radiansToTurn);
        devices.getWheels().spinAngle(degrees);
    }

    private boolean isCorrectlyOrientet(SensoryInputs input, ResponseDevices devices) {
        Pair<Integer, Double> smallestReading = input.getLightArray().getSmallestValueAndIndex();
        return ((smallestReading.s == 0 || smallestReading.s == 7) && (Math.abs(input.getLightArray().getLightSensorValue(0) - input.getLightArray().getLightSensorValue(7)) < frontLightRealignThreshold));
    }

    private void moveForward(SensoryInputs input, ResponseDevices devices) {
        devices.getWheels().forward();
    }

    private static double radiansToDegrees(double radians) {
        return radians * 180.0 / Math.PI;
    }

    private void pushBox(SensoryInputs input, ResponseDevices devices) {        
        ProximityArray proximity = input.getProximityArray();
        double frontalDiff = proximity.getDistanceSensorValue(0) - proximity.getDistanceSensorValue(7);
        
        if (Math.abs(frontalDiff) > pushBoxAlignmentThreshold) {
            alignWithBox(frontalDiff, devices);
        } else {
            moveForward(input, devices);
        }
    }

    private void alignWithBox(double frontalDiff, ResponseDevices devices) {
        setSpeedBasedOnAngle(frontalDiff / 10000, devices);
    }

    private void setSpeedBasedOnAngle(double angle, ResponseDevices devices) {
        double left = 1.0, right = 1.0;
        double coef = 3.0;
        if (angle > 0) {
            right -= angle*coef;
            right *= -1;
        } else {
            left += angle*coef;
            left *= -1;
        }
        devices.getWheels().moveWheels(left, right);
    }
}
