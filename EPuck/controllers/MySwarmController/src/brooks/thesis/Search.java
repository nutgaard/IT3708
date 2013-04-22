/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brooks.thesis;

import brooks.Behaviour;
import device.ResponseDevices;
import device.SensoryInputs;

/**
 *
 * @author Nicklas
 */
public class Search implements Behaviour {

    private static final int proximityThreshold = 250;
    private static final int continuousCounterLimit = 20;
    private static final boolean[][] cases = {
        {false, false, false, false, true, true},
        {false, false, false, true, true, false},
        {false, false, true, false, true, false},
        {false, false, true, true, true, false},
        {false, true, false, false, false, true},
        {false, true, false, true, true, false},
        {false, true, true, false, false, true},
        {false, true, true, true, true, false},
        {true, false, false, false, false, true},
        {true, false, false, true, true, false},
        {true, false, true, false, false, true},
        {true, false, true, true, true, false},
        {true, true, false, false, false, true},
        {true, true, false, true, true, false},
        {true, true, true, false, false, true},
        {true, true, true, true, false, true}
    };
    private int continuousCounter = 0;
    private double randDoubleLeft, randDoubleRight, leftWheelSpeed, rightWheelSpeed;

    public Search() {
    }

    @Override
    public boolean trigger(SensoryInputs input, ResponseDevices devices) {
        return true;
    }

    @Override
    public void execute(SensoryInputs input, ResponseDevices devices) {
        double[] sensorValues = input.getProximityArray().getValues();
        double[] frontSensors = {sensorValues[6], sensorValues[7], sensorValues[0], sensorValues[1]};
        calculateThreshold(frontSensors, proximityThreshold);
        devices.getWheels().moveWheels(leftWheelSpeed/1000., rightWheelSpeed/1000.);
    }

    private void calculateThreshold(double[] sensorValues, int threshold) {
        boolean[] thresholdArray = new boolean[sensorValues.length];
        for (int i = 0; i < sensorValues.length; i++) {
            if (sensorValues[i] > threshold) {
                thresholdArray[i] = true;
            }
        }
        calculateSeachSpeed(thresholdArray);
    }

    private void calculateSeachSpeed(boolean[] thresholdArray) {
        continuousCounter++;

        for (int caseId = 0; caseId < cases.length; caseId++) {
            if (matchFromTo(cases[caseId], thresholdArray, 0, 4)) {
                if (continuousCounter == continuousCounterLimit) {;
                    resetCounter();
                }
                if (cases[caseId][4] == cases[caseId][5]) {
                    leftWheelSpeed = (randDoubleLeft * 500) + 500;
                    rightWheelSpeed = (randDoubleRight * 500) + 500;
                } else if (cases[caseId][4] && !cases[caseId][5]) {
                    leftWheelSpeed -= 300;
                    rightWheelSpeed = 700;
                } else {
                    leftWheelSpeed = 700;
                    rightWheelSpeed -= 300;
                }
                break;
            }
        }
    }

    private boolean matchFromTo(boolean[] a, boolean[] b, int start, int end) {
        for (int i = start; i < end; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    private void resetCounter() {
        continuousCounter = 0;
        setRandomSpeeds();
    }

    private void setRandomSpeeds() {
        randDoubleLeft = Math.random();
        randDoubleRight = Math.random();
    }
}
