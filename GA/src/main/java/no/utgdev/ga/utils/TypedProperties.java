/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.utgdev.ga.utils;

import java.util.Properties;

/**
 *
 * @author Nicklas
 */
public class TypedProperties {

    private Properties props;

    public TypedProperties() {
        this.props = new Properties();
    }

    public TypedProperties(Properties defaults) {
        this.props = new Properties(defaults);
    }
    public Properties getProperties() {
        return this.props;
    }
    void setProperties(Properties props) {
        this.props = props;
    }

    public byte getByte(String key, byte defaultValue) {
        return Byte.parseByte(props.getProperty(key, String.valueOf(defaultValue)));
    }

    public short getShort(String key, short defaultValue) {
        return Short.parseShort(props.getProperty(key, String.valueOf(defaultValue)));
    }

    public int getInt(String key, int defaultValue) {
        return Integer.parseInt(props.getProperty(key, String.valueOf(defaultValue)));
    }

    public long getLong(String key, long defaultValue) {
        return Long.parseLong(props.getProperty(key, String.valueOf(defaultValue)));
    }

    public float getFloat(String key, float defaultValue) {
        return Float.parseFloat(props.getProperty(key, String.valueOf(defaultValue)));
    }

    public double getDouble(String key, double defaultValue) {
        return Double.parseDouble(props.getProperty(key, String.valueOf(defaultValue)));
    }

    public char getChar(String key, char defaultValue) {
        return props.getProperty(key, String.valueOf(defaultValue)).toCharArray()[0];
    }

    public String getString(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.parseBoolean(props.getProperty(key, String.valueOf(defaultValue)));
    }

    public void setByte(String key, byte value) {
        props.setProperty(key, String.valueOf(value));
    }

    public void setShort(String key, short value) {
        props.setProperty(key, String.valueOf(value));
    }

    public void setInt(String key, int value) {
        props.setProperty(key, String.valueOf(value));
    }

    public void setLong(String key, long value) {
        props.setProperty(key, String.valueOf(value));
    }

    public void setFloat(String key, float value) {
        props.setProperty(key, String.valueOf(value));
    }

    public void setDouble(String key, Double value) {
        props.setProperty(key, String.valueOf(value));
    }

    public void setChar(String key, char value) {
        props.setProperty(key, String.valueOf(value));
    }

    public void setString(String key, String value) {
        props.setProperty(key, value);
    }

    public void setBoolean(String key, boolean value) {
        props.setProperty(key, String.valueOf(value));
    }
}
