package metrotracker;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import metrotracker.api.WmataApi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

@WebListener
public class Application implements ServletContextListener{
    private static final Logger log = Logger.getLogger(Application.class.getName());
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();

        try {
            InputStream is = context.getResourceAsStream("/WEB-INF/config.properties");
            if(is != null) {  //TODO: clean this up! pass config file location as -D ?
                AppProperties.loadProperties(is);
            }
            else {
                log.warning("Unable to locate /WEB-INF/config.properties file. Configurations not loaded");
            }
        } catch (IOException e) {
            log.severe("Unable to read /WEB-INF/config.properties file: " + e.toString());
        }
        //Read API key from config, and set instance on the context.
        String apiKey = AppProperties.getConfiguration(AppProperties.Configuration.WMATA_API_KEY);
        if("".equals(apiKey)) {  //TODO: AppProperties.loadProperties should check for "required" configurations and generate appropriate warnings, instead of checking here.
            log.warning("API key not found in configuration file. Please ensure an API key is defined. " + AppProperties.Configuration.WMATA_API_KEY.toString());
        }
        log.info("Loaded API key from configuration file: " + apiKey);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) { }
}
