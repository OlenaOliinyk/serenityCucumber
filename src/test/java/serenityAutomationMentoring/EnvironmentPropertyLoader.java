package serenityAutomationMentoring;

public class EnvironmentPropertyLoader {

    public static String getProperty(final String env1) {
        String propertyValue = System.getProperty("environment.config");
        return propertyValue;
    }


}
