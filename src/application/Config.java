package application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static String token;
    private static String baseUrl;

    static {
        try (InputStream input = Config.class.getResourceAsStream("/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            token = prop.getProperty("canvas.token");
            baseUrl = prop.getProperty("canvas.baseUrl");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error: Could not load config.properties.");
        }
    }

    public static String getToken() {
        return token;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}