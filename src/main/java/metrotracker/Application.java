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
        Properties props = new Properties();

        try {
            InputStream is = context.getResourceAsStream("/WEB-INF/config.properties");
            if(is != null) {  //TODO: clean this up! pass config file location as -D ?
                props.load(is);
            }
        } catch (IOException e) {
            log.severe("Unable to load API key");
        }
        //Read API key from config, and set instance on the context.
        String myApiKey = props.getProperty("api.key");
        if(myApiKey == null) {
            log.severe("API key was not found in configuration file");
        }
        log.info("Instantiating API with key: " + myApiKey);
        WmataApi api = new WmataApi(myApiKey);
        context.setAttribute("wmataApi", api);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) { }
}
