/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.trackergame;

/**
 *
 * @author Nicklas
 */
public interface TrackController {
    public int move(boolean[] shadows);
    public void objectDone(double overlap, int objectSize);
}
