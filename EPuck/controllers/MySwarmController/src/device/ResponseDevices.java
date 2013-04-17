/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package device;

import device.output.LEDArray;
import device.output.Wheels;

/**
 *
 * @author Nicklas
 */
public class ResponseDevices {
    private LEDArray ledArray;
    private Wheels wheels;

    public ResponseDevices(LEDArray ledArray, Wheels wheels) {
        this.ledArray = ledArray;
        this.wheels = wheels;
    }

    public LEDArray getLedArray() {
        return ledArray;
    }

    public Wheels getWheels() {
        return wheels;
    }
    
    
}
