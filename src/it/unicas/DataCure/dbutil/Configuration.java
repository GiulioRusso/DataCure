package it.unicas.DataCure.dbutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static Properties properties;

    public static void loadProperties() {
        properties = new Properties();
        try {
            String configPath = "/Users/giuliorusso/Library/Mobile Documents/com~apple~CloudDocs/Documents/Istruzione/Universita/Magistrale/Distributed Programming/Progetto/DataCure/src/it/unicas/DataCure/dbutil/config.properties";
            FileInputStream fileInputStream = new FileInputStream(configPath);
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPathVariable(String key) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);
    }
}

