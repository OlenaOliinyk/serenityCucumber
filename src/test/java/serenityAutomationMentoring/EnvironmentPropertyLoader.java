package serenityAutomationMentoring;


import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

public class EnvironmentPropertyLoader {

    public static String getProperty(final String env1) throws IOException {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("env1.properties");
        prop.load(stream);

        return env1;
    }

    }
