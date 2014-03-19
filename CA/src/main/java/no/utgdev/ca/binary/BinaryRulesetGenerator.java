/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ca.binary;

import java.util.LinkedList;
import java.util.List;
import no.utgdev.ca.core.Rule;
import no.utgdev.ca.core.RuleSet;
import no.utgdev.ca.utils.BinaryUtils;

/**
 *
 * @author Nicklas
 */
public class BinaryRulesetGenerator {
    public static RuleSet generate(boolean[] resultVector, int matchingSize) {
        List<Rule> rules = new LinkedList<Rule>();        
        for (int i = 0; i < resultVector.length; i++) {
            boolean[] bArr = BinaryUtils.intToBooleanArray(i, matchingSize);
            boolean result = resultVector[i];
            rules.add(new BinaryRule(bArr, result));
        }
        return new RuleSet(rules);
    }
}
