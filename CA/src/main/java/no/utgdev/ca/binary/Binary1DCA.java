/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ca.binary;

import java.util.Arrays;
import no.utgdev.ca.core.CA;
import no.utgdev.ca.core.CACell;
import no.utgdev.ca.utils.Pair;

/**
 *
 * @author Nicklas
 */
public class Binary1DCA implements CA {
    private CACell[] cells;
    private int neighbourSize;
    
    public Binary1DCA(Binary1DCA ca) {
        this.cells = new BinaryCACell[ca.cells.length];
        this.neighbourSize = ca.neighbourSize;
        for (int i = 0; i < this.cells.length; i++) {
            this.cells[i] = new BinaryCACell((Boolean)ca.cells[i].getValue());
        }
    }
    public Binary1DCA(CACell[] cells, int neighbourSize) {
        this.cells = cells;
        this.neighbourSize = neighbourSize;
    }

    public Pair<CACell, CA>[] getCellSubsets() {
        Pair<CACell, CA>[] subsets = new Pair[cells.length];
        int subsetSize = 2*neighbourSize+1;
        for (int i = 0; i < cells.length; i++) {
            int nCount = 0;
            CACell[] subset = new CACell[subsetSize];
            for (int n = -neighbourSize; n <= neighbourSize; n++) {
                subset[nCount++] = get(i+n);
            }
            subsets[i] = new Pair<CACell, CA>(cells[i], new Binary1DCA(subset, neighbourSize));
        }
        return subsets;
    }
    private CACell get(int i) {
        while (i < 0){
            i += this.cells.length;
        }
        while (i >= this.cells.length) {
            i -= this.cells.length;
        }
        return this.cells[i];
    }

    public CA deepCopy() {
        return new Binary1DCA(this);
    }

    public CACell[] getCellArray() {
        return this.cells;
    }

    @Override
    public String toString() {
        return "Binary1DCA{" + "cells=" + Arrays.toString(cells) + '}';
    }
    
}
