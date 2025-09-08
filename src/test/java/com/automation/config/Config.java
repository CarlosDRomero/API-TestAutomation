package com.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final String PROPERTIES_FILE = "src/test/resources/config.properties";
    private static final Properties PROPERTIES = new Properties();

    public static void loadProperties() {
        try {
            // La idea de esta condición es que cada vez que se llame a esta función se evite volver a leer el archivo si ya fue cargado
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
        // La idea es asegurarse de que las configuraciones están cargadas antes de intentar acceder a alguna propiedad del archivo
        loadProperties();
        return PROPERTIES.getProperty(propertyName);
    }
}
