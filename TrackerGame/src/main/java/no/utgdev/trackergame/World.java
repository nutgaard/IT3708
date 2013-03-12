/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.trackergame;

/**
 *
 * @author Nicklas
 */
public class World {

    private int width, height, objectHeight, objectVelocity, trackerX, trackerSize, objectX, objectSize;
    private boolean[] objectRow;
    private boolean[] trackerRow;
    private TrackController controller;

    public World(int width, int height, int trackerSize, TrackController controller) {
        this.width = width;
        this.height = height;
        this.controller = controller;

        createTracker((height - trackerSize) / 2, trackerSize);
        createObject((int) (Math.random() * width), (int) (Math.random() * 5)+1, 0, 14);
    }

    private void createTracker(int x, int size) {
        this.trackerRow = new boolean[width];
        this.trackerX = x;
        this.trackerSize = size;
        wraparoundFill(this.trackerRow, x, x + size, true);
    }

    private void createObject(int x, int size, int velocity, int objectheight) {
        this.objectX = x;
        this.objectSize = size;
        this.objectRow = new boolean[width];
        wraparoundFill(this.objectRow, x, x + size, true);
        this.objectHeight = objectheight;
        this.objectVelocity = velocity;//May be changed later
    }

    private void wraparoundFill(boolean[] b, int from, int to, boolean value) {
        if (from >= to) {
            throw new IllegalArgumentException("From equals to, WTF");
        }
        int nFrom = (from + width) % width;
        int nTo = (to + width) % width;
        for (int i = 0, s = to-from; i < s; i++) {
            b[(nFrom+i) % width] = value;
        }
    }

    public void update() {
        boolean[] shadow = new boolean[trackerSize];
        if (objectRow != null) {
            for (int i = 0; i < trackerSize; i++) {
                int s = (trackerX + i + width) % width;
                shadow[i] = objectRow[s] & trackerRow[s];
            }
        }
        //Update tracker
        int m = controller.move(shadow);
        createTracker(trackerX + m, trackerSize);

        //Update object
        if (objectHeight != -1) {
            objectHeight--;
            createObject(objectX + objectVelocity, objectSize, 0, objectHeight);
        }

        if (objectHeight == 0) {
            int shadowCount = 0;
            for (boolean b : shadow) {
                if (b) {
                    shadowCount++;
                }
            }
            double overlap = (shadowCount * 1.0) / objectSize;
            controller.objectDone(overlap, objectSize);
            createObject((int) (Math.random() * width), (int) (Math.random() * 5)+1, 0, 14);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getObjectHeight() {
        return objectHeight;
    }

    public boolean[] getObjectRow() {
        return objectRow;
    }

    public boolean[] getTrackerRow() {
        return trackerRow;
    }
}
