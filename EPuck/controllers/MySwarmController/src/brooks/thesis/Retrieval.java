/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brooks.thesis;

import brooks.Behaviour;
import device.ResponseDevices;
import device.SensoryInputs;
import device.input.LightArray;

/**
 *
 * @author Nicklas
 */
public class Retrieval implements Behaviour {

    private static final int IRThreshold = 3700;
    private static final int PushThreshold = 500;
    private static final int numberOfSensors = 8;
    private double leftWheelSpeed, rightWheelSpeed;
    private boolean push;
    private boolean[] leds = new boolean[10];

    public Retrieval() {
    }

    @Override
    public boolean trigger(SensoryInputs input, ResponseDevices devices) {
        LightArray la = input.getLightArray();
        return (la.getSmallestValueAndIndex().t < IRThreshold);
    }

    @Override
    public void execute(SensoryInputs input, ResponseDevices devices) {
        double[] sensorValues = input.getLightArray().getValues();
        selectBehaviour(sensorValues);
        if (push) {
            pushBox(sensorValues, IRThreshold);
        } else {
            convergeToBox(sensorValues, IRThreshold);
        }
        for (int i = 0; i < leds.length; i++) {
            if (leds[i]){
                devices.getLedArray().setLEDState(i, true);
            }else {
                devices.getLedArray().setLEDState(i, false);
            }
        }
        devices.getWheels().moveWheels(leftWheelSpeed/1000., rightWheelSpeed/1000.);
    }

    private void selectBehaviour(double[] values) {
        push = false;
        for (int i = 0; i < numberOfSensors; i++) {
            if (values[i] < PushThreshold) {
                push = true;
                break;
            }
        }
    }

    private void pushBox(double[] sensorValues, int IRThreshold) {
        leftWheelSpeed = 0;
        rightWheelSpeed = 0;

        for (int i = 0; i < numberOfSensors; i++) {
            leds[i] ^= true;
            if (sensorValues[i] < IRThreshold) {
                updateSpeed(i);
            }
        }
        if (sensorValues[0] < IRThreshold && sensorValues[7] < IRThreshold) {
            leftWheelSpeed = 1000;
            rightWheelSpeed = 1000;
        }
    }

    private void convergeToBox(double[] sensorValues, int IRThreshold) {
        leftWheelSpeed = 0;
        rightWheelSpeed = 0;
        for (int i = 0; i < numberOfSensors; i++) {
            if (sensorValues[i] < IRThreshold) {
                leds[i] = true;
                updateSpeed(i);
                break;
            }else {
                leds[i] = false;
            }
        }
    }

    private void updateSpeed(int IR_number) {
        if (IR_number == 0) {
            leftWheelSpeed = leftWheelSpeed + 700;
        } else if (IR_number == 7) {
            rightWheelSpeed = rightWheelSpeed + 700;
        } else if (IR_number == 1) {
            leftWheelSpeed = leftWheelSpeed + 350;
        } else if (IR_number == 6) {
            rightWheelSpeed = rightWheelSpeed + 350;
        } else if (IR_number == 2) {
            leftWheelSpeed = leftWheelSpeed + 550;
            rightWheelSpeed = rightWheelSpeed - 300;
        } else if (IR_number == 5) {
            rightWheelSpeed = rightWheelSpeed + 550;
            leftWheelSpeed = leftWheelSpeed - 300;
        } else if (IR_number == 3) {
            leftWheelSpeed = leftWheelSpeed + 500;
        } else if (IR_number == 4) {
            rightWheelSpeed = rightWheelSpeed + 500;
        }
    }
}
