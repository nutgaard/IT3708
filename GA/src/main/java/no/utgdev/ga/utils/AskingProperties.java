/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;
import org.javatuples.Pair;

/**
 *
 * @author Nicklas
 */
public class AskingProperties {

    private Properties props;
    private Pair<String, String>[] propsCopy;

    
    public AskingProperties(Properties props) {
        this.props = props;
    }

    private void copyBuilder() {
        Enumeration e = props.propertyNames();
        System.out.println("Size: "+props.size());
        propsCopy = new Pair[props.size()];
        int i = 0;
        while (e.hasMoreElements()) {
            String elem = e.nextElement().toString();
            propsCopy[i++] = new Pair<String, String>(elem, props.getProperty(elem));
        }
    }

    private void presentChoice() {
        if (propsCopy == null) {
            copyBuilder();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < propsCopy.length; i++) {
            Pair<String, String> p = propsCopy[i];
            sb.append(i).append(") ").append(p.getValue0()).append(": ").append(p.getValue1()).append("\n");
        }
        sb.append(propsCopy.length).append(") Add property\n");
        sb.append(propsCopy.length + 1).append(") Load from file\n");
        sb.append(propsCopy.length + 2).append(") Save to file\n");
        sb.append(propsCopy.length + 3).append(") Run task\n");
        System.out.println(sb.toString());
    }

    public Properties ask() {
        Scanner in = new Scanner(System.in);
        boolean ask = true;
        while (ask) {
            copyBuilder();
            presentChoice();
            int choice = in.nextInt();
            ask = !choiceParser(choice, in);
        }
        return props;
    }

    private boolean choiceParser(int choice, Scanner in) {
        if (choice >= 0 && choice < propsCopy.length) {
            System.out.println("Enter new value for " + propsCopy[choice].getValue0() + ": ");
            String value = in.next();
            props.setProperty(propsCopy[choice].getValue0(), value);
            return false;
        } else {
            try {
                int diff = choice - propsCopy.length;
                switch (diff) {
                    case 0:
                        System.out.println("Enter property key: ");
                        String key = in.next();
                        System.out.println("Enter property value: ");
                        String value = in.next();
                        props.setProperty(key, value);
                        return false;
                    case 1:
                        System.out.println("Enter relative path to load: ");
                        value = in.next();
                        Properties properties = new Properties();
                        properties.loadFromXML(new FileInputStream(new File(value)));
                        props = properties;
                        return false;
                    case 2:
                        System.out.println("Enter relative path to save to: ");
                        value = in.next();
                        props.storeToXML(new FileOutputStream(new File(value)), null);
                        return false;
                    case 3:
                        System.out.println("Starting the beast");
                        return true;
                    default:
                        System.out.println("Could not find action for index " + choice);
                        return false;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
