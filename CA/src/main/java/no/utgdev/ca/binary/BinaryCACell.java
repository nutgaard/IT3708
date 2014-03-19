/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ca.binary;

import no.utgdev.ca.core.CACell;

/**
 *
 * @author Nicklas
 */
public class BinaryCACell extends CACell<Boolean> {
    private boolean value;
    
    public BinaryCACell() {
        this(false);
    }
    public BinaryCACell(boolean value) {
        this.value = value;
    }

    @Override
    public void setValue(Boolean result) {
        this.value = result;
    }

    @Override
    public Boolean getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value+"";
    }
    
    
}
