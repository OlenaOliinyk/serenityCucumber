package serenityAutomationMentoring;

import org.apache.log4j.Logger;

//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import serenityAutomationMentoring.properties.PropertiesNames;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;


public class EnvironmentPropertyLoader {
    private static Logger log = org.apache.log4j.Logger.getLogger(EnvironmentPropertyLoader.class);


   // private static Logger log = LoggerFactory.getLogger(EnvironmentPropertyLoader.class);


    private static AtomicReference<EnvironmentPropertyLoader> instance = new AtomicReference<>(null);

    private Properties properties = new Properties();

    private EnvironmentPropertyLoader(final String propertyFile) {
        try {
            properties.putAll(loadPropertiesFromFile(propertyFile));
        } catch (final IllegalStateException e) {
            throw new IllegalStateException("Failed to load environment configuration file", e);
        }
    }

    public static String getProperty(final String propertyName) {
        if (instance.get() == null) {
            instance.compareAndSet(null, new EnvironmentPropertyLoader(System.getProperty(PropertiesNames.ENV_CONFIG_FILE)));
        }
        return System.getProperty(propertyName, instance.get().properties.getProperty(propertyName));
    }

    private static Properties loadPropertiesFromFile(final String propertyFile) {
        log.info("Reading properties: "+ propertyFile);
        final InputStream inputStream = Optional
                .ofNullable(EnvironmentPropertyLoader.class.getResourceAsStream("/properties/" + propertyFile)).orElseThrow(
                        () -> new NullPointerException("Unable to open input stream for resource " + propertyFile));
        final Properties props = new Properties();
        try {
            props.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            //   Logger.log.info(e);

            throw new IllegalStateException("Unable to load properties from resource " + propertyFile);
        }
        for (final String propertyName : props.stringPropertyNames()) {
            if ("common.properties".equals(propertyName)) {
                props.putAll(loadPropertiesFromFile((String) props.get(propertyName)));
            }
        }
        return props;
    }

    public static void addPropertiesFromFile(final String propertyFile) {
        instance.compareAndSet(null, new EnvironmentPropertyLoader(propertyFile));
    }

}
