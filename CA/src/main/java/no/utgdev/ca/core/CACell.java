/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ca.core;

/**
 *
 * @author Nicklas
 */
public abstract class CACell<T> {

    public abstract void setValue(T result);
    public abstract T getValue();
}
