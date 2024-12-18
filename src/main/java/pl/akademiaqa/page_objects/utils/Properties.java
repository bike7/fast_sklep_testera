package pl.akademiaqa.page_objects.utils;

import java.util.ResourceBundle;

public class Properties {

    public static String getBaseUrl() {
        return getProperty("baseUrl");
    }

    public static String getBrowserType() {
        return getProperty("browser.type");
    }

    public static boolean isTracingEnabled() {
        return Boolean.parseBoolean(getProperty("tracing.enabled"));
    }

    public static boolean isBrowserHeadless() {
        return Boolean.parseBoolean(getProperty("browser.headless"));
    }

    public static int getBrowserSlowMo() {
        return Integer.parseInt(getProperty("browser.slowmo"));
    }

    private static String getProperty(String propertyName) {
        return ResourceBundle.getBundle("application").getString(propertyName);
    }
}
