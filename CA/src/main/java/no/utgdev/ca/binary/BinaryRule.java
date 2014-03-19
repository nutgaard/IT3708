/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ca.binary;

import no.utgdev.ca.core.CA;
import no.utgdev.ca.core.CACell;
import no.utgdev.ca.core.Rule;

/**
 *
 * @author Nicklas
 */
public class BinaryRule implements Rule<Boolean> {
    private boolean[] match;
    private boolean result;
    
    public BinaryRule(boolean[] match, boolean result) {
        this.match = match;
        this.result = result;
    }

    public boolean isApplicable(CA ca) {
        CACell<Boolean>[] array = ca.getCellArray();
        if (array.length != match.length) {
            return false;
        }
        for (int i = 0; i < array.length;i++) {
            if (array[i].getValue() != match[i]) {
                return false;
            }
        }
        return true;
    }

    public Boolean getResult() {
        return result;
    }
    
}
