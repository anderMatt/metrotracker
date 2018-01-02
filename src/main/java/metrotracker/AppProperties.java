package metrotracker;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class AppProperties {
    private static final Logger log = Logger.getLogger(AppProperties.class.getName());
    public enum Configuration {
        WMATA_API_KEY("wmata.api.key", "Key for authenticating to WMATA's REST API.");

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public String toString() {
            return key + ": " + value;
        }

        private String key;
        private String value;

        Configuration(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private static Properties props = new Properties();
    public static Properties getProperties() {
        return props;
    }

    public static void loadProperties(InputStream is) throws IOException {
        props.load(is);
    }

    public static String getConfiguration(Configuration conf) {
        return getConfiguration(conf.getKey());
    }

    public static String getConfiguration(String confName) {
        return props.get(confName) == null ? "" : props.get(confName).toString();
    }
}
