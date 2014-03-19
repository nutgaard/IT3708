/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ca;

import no.utgdev.ca.binary.Binary1DCA;
import no.utgdev.ca.binary.BinaryCACell;
import no.utgdev.ca.binary.BinaryRulesetGenerator;
import no.utgdev.ca.core.CA;
import no.utgdev.ca.core.CACell;
import no.utgdev.ca.core.CALoop;
import no.utgdev.ca.core.RuleSet;

/**
 *
 * @author Nicklas
 */
public class CATest {
    public static void main(String[] args) {
        RuleSet rules = BinaryRulesetGenerator.generate(new boolean[]{
//            true, false,
//            false, true,
//            true, false,
//            false, true
            false, false, false, true, false, true, true, true
        }, 3);
        CACell[] cells = new CACell[50];
        for (int i = 0; i < 50; i++) {
            cells[i] = new BinaryCACell((Math.random() > 0.7) ? true : false);
        }
        CA ca = new Binary1DCA(cells, 1);
        
        CALoop loop = new CALoop(ca, rules, 500);
        CA result = loop.run();
        System.out.println(result);
    }
}
