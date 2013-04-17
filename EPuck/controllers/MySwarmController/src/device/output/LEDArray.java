/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package device.output;

import com.cyberbotics.webots.controller.LED;
import com.cyberbotics.webots.controller.Robot;

/**
 *
 * @author Nicklas
 */
public class LEDArray {
    private LED[] LEDs;
    
    public LEDArray(Robot robot, int numberOfLEDs) {
        this.LEDs = new LED[numberOfLEDs];
        for (int i = 0; i < numberOfLEDs; i++) {
            this.LEDs[i] = robot.getLED("led"+i);
        }
    }
    public boolean isLedOn(int id) {
        return getLED(id).get() == 1;
    }
    public void setLEDState(int id, boolean LEDValueOn) {
        int value = (LEDValueOn) ? 1 : 0;
        getLED(id).set(value);
    }
    public LED getLED(int id) {
        return this.LEDs[id];
    }
    public void switchLEDState(int id) {
        setLEDState(id, !isLedOn(id));
    }
}
