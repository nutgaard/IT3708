package no.utgdev.ca.core;

import no.utgdev.ca.utils.Pair;

public class CALoop {
    private CA ca, prev;
    private RuleSet rules;
    private int maxTime;
    
    public CALoop(CA ca, RuleSet rules, int maxTime) {
        this.ca = ca;
        this.rules = rules;
        this.maxTime = maxTime;
    }
    
    public CA run() {
        int time = 0;
        while (time < maxTime && !ca.equals(prev)) {
            System.out.println(time+" "+ca);
            prev = ca;
            ca = evolve(ca);
            time++;
        }
        return ca;
    }
    private CA evolve(CA ca) {
        CA clone = ca.deepCopy();
        Pair<CACell, CA>[] subsets = clone.getCellSubsets();
        for (Pair<CACell, CA> subset : subsets) {
            Rule applicableRule = rules.findApplicableRule(subset.second);
            subset.first.setValue(applicableRule.getResult());
        }
        return clone;
    }
}
