/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brooks.impl;

/**
 *
 * @author Nicklas
 */
public enum RepositioningState {

    NONE(null, 0), FORWARD(NONE, 50), TURNFORWARD(FORWARD, 90), OFFSET(TURNFORWARD, 50), TURNOFFSET(OFFSET, 90), REVERSE(TURNOFFSET, 25);
    
    private RepositioningState nextState;
    private int timestepLimit;

    private RepositioningState(RepositioningState nextState, int timestepLimit) {
        this.nextState = nextState;
        this.timestepLimit = timestepLimit;
    }

    public RepositioningState getNextState() {
        return (nextState != null) ? nextState : NONE;
    }
    public int getTimestepLimit() {
        return timestepLimit;
    }
}
