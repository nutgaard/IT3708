
import brooks.ControlSystem;
import com.cyberbotics.webots.controller.Robot;

public class MySwarmController extends Robot {

    private ControlSystem cs;

    public MySwarmController() {
        super();
        cs = new ControlSystem(this);
    }

    public void run() {
        while (step(64) != -1) {
            cs.update();
        }
    }

    public static void main(String[] args) {
        MySwarmController controller = new MySwarmController();
        controller.run();
    }
}
