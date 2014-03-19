/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ca.core;

/**
 *
 * @author Nicklas
 */
public interface Rule<T> {
    public boolean isApplicable(CA ca);
    public T getResult();
}
