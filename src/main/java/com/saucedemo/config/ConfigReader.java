package com.saucedemo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    /*
        utility class, which loads configuration properties from a file
        and allows runtime overrides via JVM system properties.
* */
    private static final Properties PROPS = new Properties();
    static {
        try(InputStream is = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if(is == null){
                throw new RuntimeException("config.properties not found on classpath");
            }
            PROPS.load(is);
        }catch(IOException e){
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private ConfigReader() {};
    // Main getter method
    public static String get(String key){
        String override = System.getProperty(key);
        return override != null ? override : PROPS.getProperty(key);
    }

    public static boolean getBoolean(String key){ return Boolean.parseBoolean(get(key));}
    public static int getInteger(String key){ return Integer.parseInt(get(key));}
}

