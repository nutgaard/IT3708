/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ca.core;

import java.util.List;

/**
 *
 * @author Nicklas
 */
public class RuleSet {
    private List<Rule> rules;

    public RuleSet(List<Rule> rules) {
        this.rules = rules;
    }
    
    public Rule findApplicableRule(CA subset) {
        for (Rule rule : rules) {
            if (rule.isApplicable(subset)){
                return rule;
            }
        }
        throw new IllegalArgumentException("No matching rule found");
    }
    
}
