package metrotracker;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@WebListener
public class Application implements ServletContextListener{
    private static final Logger log = Logger.getLogger(Application.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();

        try (InputStream is = context.getResourceAsStream("/WEB-INF/config.properties")) {
            if (is == null) {
                log.severe("Unable to load resource stream for config file");
            }
            AppProperties.loadProperties(is);
        } catch (IOException e) {  //nullptr thrown if resource not found.
            log.severe("Unable to read configuration file: " + e.toString());
        }

        String apiKey = AppProperties.getProperty(AppProperties.Configuration.WMATA_API_KEY);
        if(apiKey == null) {
            throw new RuntimeException("WMATA API key was not provided! Ensure config file contains an\"" +
                    "API key entry: " + AppProperties.Configuration.WMATA_API_KEY.toString());
        }
        log.info("Using WMATA API key loaded from config file: " + apiKey);
        context.setAttribute(AppProperties.Configuration.WMATA_API_KEY.getKey(), apiKey);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) { }
}
