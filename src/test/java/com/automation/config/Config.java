package com.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final String PROPERTIES_FILE = "src/test/resources/config.properties";
    private static final Properties PROPERTIES = new Properties();

    public static void loadProperties() {
        try {
            if (PROPERTIES.isEmpty()) {
                System.out.println("Loading properties file...");
                FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE);
                PROPERTIES.load(fileInputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String propertyName) {
        loadProperties();
        return PROPERTIES.getProperty(propertyName);
    }
}
