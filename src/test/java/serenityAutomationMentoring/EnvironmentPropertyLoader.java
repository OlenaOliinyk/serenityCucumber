package serenityAutomationMentoring;







import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

public class EnvironmentPropertyLoader {
   // private static Logger log = LoggerFactory.getLogger(EnvironmentPropertyLoader.class);

    public static String getProperty(final String env1) {
        String propertyValue = System.getProperty("environment.config");

        return propertyValue;
    }


}
