package device.output;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.Robot;

/**
 *
 * @author Nicklas
 */
public class Wheels extends DifferentialWheels {

    private static final double maxWheelSpeed = 1000;
    private static final double encodreResolution = 159.23;
    private static final double tempo = 0.5;
    private static final double wheelDiameter = 4.1;
    private static final double axleLength = 5.3;
    
    private int timestep;

    public Wheels(Robot robot) {
        super();
        this.timestep = (int)robot.getBasicTimeStep();
        enableEncoders(timestep);
    }
    
    public void forward() {
        forward(1, 1);
    }
    public void forward(double speed) {
        forward(speed, 1);
    }
    public void forward(double speed, int duration) {
        move(speed, duration);
    }
    public void backward() {
        backward(1, 1);
    }
    public void backward(double speed) {
        backward(speed, 1);
    }
    public void backward(double speed, int duration) {
        move(-speed, duration);
    }

    public void move(double speed, int duration) {
        double s = Math.max(-1.0, Math.min(1.0, speed));
        setWheelSpeed(s, s);
//        step(duration);
    }
    public void moveWheels(double leftWheelSpeed, double rightWheelSpeed) {
        moveWheels(leftWheelSpeed, rightWheelSpeed, 1);
    }
    public void moveWheels(double leftWheelSpeed, double rightWheelSpeed, int duration) {
        double ls = Math.max(-1.0, Math.min(1.0, leftWheelSpeed));
        double rs = Math.max(-1.0, Math.min(1.0, rightWheelSpeed));
        setWheelSpeed(ls, rs);
//        step(duration);
    }
    public void setWheelSpeed(double leftWheelSpeed, double rightWheelSpeed) {
        double ms = tempo*maxWheelSpeed;
        this.setSpeed(ms*leftWheelSpeed, ms*rightWheelSpeed);
    }

    public void turnLeft() {
        spinAngle(90);
    }

    public void turnRight() {
        spinAngle(-90);
    }
    public void spinCW(double speed) {
        spinCW(speed, 1);
    }
    public void spinCW(double speed, int duration) {
        spin(speed, duration, true);
    }
    public void spinCCW(double speed) {
        spinCCW(speed, 1);
    }
    public void spinCCW(double speed, int duration) {
        spin(speed, duration, false);
    }
    public void spin(double speed, int duration, boolean CW) {
        double s = (int)(Math.max(-1.0, Math.min(1.0, speed))*tempo*maxWheelSpeed);
        if (CW) {
            setWheelSpeed(s, -s);
        }else {
            setWheelSpeed(-s, s);
        }
//        step(duration);
    }
    public void spinAngle(double angle) {
        double a = Math.abs(angle)*Math.PI/180.0;
        
        if (angle > 0) {
            setWheelSpeed(1, -1);
        }else {
            setWheelSpeed(-1, 1);
        }
        this.setEncoders(0, 0);
        double totalUnits = a * encodreResolution * axleLength / wheelDiameter;
        double current = Math.abs(getLeftEncoder());
        double goalUnits = current + totalUnits;
        while (current < goalUnits){
            step(timestep);
            current = Math.abs(getLeftEncoder());
        }
        stop();
    }
    public void stop() {
        setWheelSpeed(0, 0);
//        step(timestep);
    }
}
