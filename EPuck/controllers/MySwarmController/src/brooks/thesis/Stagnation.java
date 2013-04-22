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
public class Stagnation implements Behaviour {

    public static final int IRDiffThreshold = 4;
    public static final int distanceDiffThreshold = 10;
    public static final int reverseLimit = 20;
    public static final int turnLimit = 10;
    public static final int forwardLimit = 40;
    public static final int neightborLimit = 300;
    public static final int alignStraightThreshold = 10;
    public static final int lowDistValue = 10;
    private double leftWheelSpeed = 0;
    private double rightWheelSpeed = 0;
    private double[] previousDistance = new double[8];
    private boolean[] leds = new boolean[10];
    private boolean hasRecovered = true, greenLedState, turnLeft;
    private int alignCounter, reverseCounter, turnCounter, forwardCounter, twice;
    private SensoryInputs input;
    private ResponseDevices devices;

    @Override
    public boolean trigger(SensoryInputs input, ResponseDevices devices) {
        this.input = input;
        this.devices = devices;

        if (input.getLightArray().getSmallestValueAndIndex().t > 1000) {
            return false;
        }
        hasRecovered = true;
        evaluatePushing(input.getProximityArray().getValues(), previousDistance);
        previousDistance = input.getProximityArray().getValues();
        if (hasRecovered) {
            resetStagnation();
        }
        return !hasRecovered;
    }

    public void evaluatePushing(double[] distance, double[] previousDistance) {
        double distDiff7 = previousDistance[7] - distance[7];
        double distDiff0 = previousDistance[0] - distance[0];

        if (Math.abs(distDiff7) > distanceDiffThreshold && Math.abs(distDiff0) > distanceDiffThreshold) {
            hasRecovered = true;
            greenLedState = false;
            alignCounter = 0;
        } else if (distance[5] > neightborLimit && distance[2] > neightborLimit) {
            hasRecovered = true;
            greenLedState = false;
            alignCounter = 0;
        } else if (distance[5] > neightborLimit || distance[2] > neightborLimit) {
            double rand = Math.random();
            if (rand > 0.5) {
                hasRecovered = true;
                greenLedState = false;
                alignCounter = 0;
            }
        }
    }

    public void stagnationRecovery(double[] distance, int distanceThreshold) {
        if (alignCounter < 2) {
            alignCounter++;
            realign(distance);
        } else if (alignCounter > 0) {
            LEDBlink();
            findNewSpot(distance, distanceThreshold);
        }
    }

    @Override
    public void execute(SensoryInputs input, ResponseDevices devices) {
        stagnationRecovery(input.getProximityArray().getValues(), distanceDiffThreshold);
        devices.getWheels().moveWheels(leftWheelSpeed / 1000., rightWheelSpeed / 1000.);
        resetStagnation();
    }

    private void realign(double[] distance) {
        double distDiffFront = distance[7] - distance[0];
        if (Math.abs(distDiffFront) > alignStraightThreshold) {
            if (distance[0] < lowDistValue) {
                rightWheelSpeed = -500;
                leftWheelSpeed = 500;
            } else if (distance[7] < lowDistValue) {
                rightWheelSpeed = 500;
                leftWheelSpeed = -500;
            } else if (distance[1] < lowDistValue) {
                rightWheelSpeed = -1000;
                leftWheelSpeed = 700;
            } else if (distance[6] < lowDistValue) {
                rightWheelSpeed = 700;
                leftWheelSpeed = -1000;
            }
        } else {
            double rand = Math.random();
            if (rand > 0.5) {
                rightWheelSpeed = -500;
                leftWheelSpeed = 500;
            } else {
                rightWheelSpeed = 500;
                leftWheelSpeed = -500;
            }
        }
        hasRecovered = true;
        greenLedState = false;
    }

    private void LEDBlink() {
        greenLedState ^= true;
    }

    private void findNewSpot(double[] distance, int distanceThreshold) {
        if (twice == 2) {
            hasRecovered = true;
            greenLedState = false;
            alignCounter = 0;
        } else if (reverseCounter != reverseLimit) {
            reverseCounter++;
            leftWheelSpeed = -800;
            rightWheelSpeed = -800;
        } else if (turnCounter != turnLimit) {
            turnCounter++;
            forwardCounter = 0;
            if (turnLeft) {
                leftWheelSpeed = -300;
                rightWheelSpeed = 700;
            } else {
                leftWheelSpeed = 700;
                rightWheelSpeed = -300;
            }
        } else if (forwardCounter != forwardLimit) {
            forwardCounter++;
            if (forwardCounter == forwardLimit - 1) {
                twice++;
                turnCounter = 0;
                turnLeft ^= true;
            }
            Search s = new Search();
            s.execute(input, devices);
            if (devices.getWheels().getLeftSpeed() > 0 && devices.getWheels().getRightSpeed() > 0) {
                rightWheelSpeed = 1000;
                leftWheelSpeed = 1000;
            }
        }
    }

    public void resetStagnation() {
        hasRecovered = true;
        reverseCounter = 0;
        turnCounter = 0;
        forwardCounter = 0;
        turnLeft = false;
        twice = 0;
    }
}
