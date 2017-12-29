package metrotracker;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class AppProperties {
    private static final Logger log = Logger.getLogger(AppProperties.class.getName());
    private static Properties props = new Properties();

    public enum Configuration  {
            WMATA_API_KEY("wmata.api.key", "Key for authenticating to WMATA's REST API");

            String key;
            String description;
            public String getKey() {
                return key;
            }
            public String getDescription() {
                return description;
            }

            public String toString() {
                return getKey() + " : " + getDescription();
            }

            Configuration(String key, String description) {
                this.key = key;
                this.description = description;
        }
    }

    protected static void loadProperties(InputStream is) throws IOException {
        props.load(is);
    }

//    public static List<String> checkRequiredPropertiesLoaded() {
//        List<String> missingProperties = new ArrayList<>();
//        for(RequiredProperties required: RequiredProperties.values()) {
//            if(props.getProperty(required.getKey()) == null ){
//                missingProperties.add(required.toString());
//            }
//        }
//        return missingProperties;
//    }

    public static Properties getProperties() {
        return props;
    }

    public static String getProperty(String propertyName) {
        return props.get(propertyName).toString();
    }

    public static String getProperty(Configuration property) {
        return props.get(property.getKey()).toString();
    }
}
