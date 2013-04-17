/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brooks;

import com.cyberbotics.webots.controller.Robot;
import device.ResponseDevices;
import device.SensoryInputs;
import device.input.LightArray;
import device.input.ProximityArray;
import device.output.LEDArray;
import device.output.Wheels;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Nicklas
 */
public class ControlSystem {
    private SensoryInputs input;
    private ResponseDevices output;
    private Queue<Behaviour> queue;

    public ControlSystem(Robot robot) {
        input = new SensoryInputs(new ProximityArray(robot, 8), new LightArray(robot, 8));
        output = new ResponseDevices(new LEDArray(robot, 10), new Wheels(robot));
        queue = new LinkedList<>();
        queue.add(new TowardsTheLight());
        queue.add(new BraitenburgAvoidance());
    }
    public void update() {
        for (Behaviour b : queue) {
            if (b.trigger(input, output)){
                System.out.println("Using: "+b);
                b.execute(input, output);
                break;
            }
        }
    }
    
}
