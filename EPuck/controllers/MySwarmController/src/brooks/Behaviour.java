/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brooks;

import device.ResponseDevices;
import device.SensoryInputs;

/**
 *
 * @author Nicklas
 */
public interface Behaviour {
    public boolean trigger(SensoryInputs input, ResponseDevices devices);
    public void execute(SensoryInputs input, ResponseDevices devices);
}
