package serenityAutomationMentoring;



import org.slf4j.LoggerFactory;
import serenityAutomationMentoring.properties.PropertiesNames;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;


public class EnvironmentPropertyLoader {

   // private static EnvironmentPropertyLoader env = new EnvironmentPropertyLoader();
//    private static Properties properties;
//
//    private EnvironmentPropertyLoader() {
//        properties = new Properties();
//        String envName = System.getProperty("environment.config");
//        String pathToConfig = String.format("configs/%s", envName);
//        try {
//            properties.load(getClass().getClassLoader().getResourceAsStream(
//                    pathToConfig));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static String getProperty(final String propName){
//        return properties.getProperty(propName);
//    }


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
        // Logger.log.info("Reading properties: %s", propertyFile);
        final InputStream inputStream = Optional
                .ofNullable(EnvironmentPropertyLoader.class.getResourceAsStream("/properties/" + propertyFile)).orElseThrow(
                        () -> new NullPointerException("Unable to open input stream for resource " + propertyFile));
        final Properties props = new Properties();
        try {
            props.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            // Logger.out.info(e);
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
