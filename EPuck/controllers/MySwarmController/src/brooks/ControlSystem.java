/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brooks;

import brooks.impl.BraitenburgAvoidance;
import brooks.impl.ConvergeAndPush;
import brooks.impl.StagnationAvoidance;
import brooks.impl.StupidConvergeAndPush;
import brooks.impl.StupidStagnationAvoidance;
import brooks.thesis.Search;
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
        
//        The good setup
//        queue.add(new StagnationAvoidance());
//        queue.add(new ConvergeAndPush());
//        queue.add(new Search());
        
//        The bad setup
        queue.add(new StupidStagnationAvoidance());
        queue.add(new StupidConvergeAndPush());
        queue.add(new BraitenburgAvoidance());
        
        
    }
    public void update() {
        for (Behaviour b : queue) {
            boolean trigger = b.trigger(input, output);
            if (trigger){
                b.execute(input, output);
                break;
            }
        }
    }
    
}
