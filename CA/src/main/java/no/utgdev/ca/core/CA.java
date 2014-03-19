/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ca.core;

import no.utgdev.ca.utils.Pair;

/**
 *
 * @author Nicklas
 */
public interface CA {   

    public Pair<CACell, CA>[] getCellSubsets();
    public CACell[] getCellArray();
    public CA deepCopy();
    
}
