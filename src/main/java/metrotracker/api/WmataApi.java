package metrotracker.api;

import metrotracker.AppProperties;
import metrotracker.WmataApiException;
import metrotracker.utli.JsonResponseReader;

import java.util.logging.Logger;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class WmataApi {
    private static final Logger log = Logger.getLogger(WmataApi.class.getName());

    public static String getBusPositions(String routeId) throws WmataApiException {
        String endpointUrl = WmataApiEndpoints.BUS_POSITIONS + "?routeId=" + routeId;
        return makeApiCall(endpointUrl);
    }

    private static String makeApiCall(String endpoint) throws WmataApiException  {
        log.info("Making API call to endpoint " + endpoint);
        String apiKey = AppProperties.getProperty(AppProperties.Configuration.WMATA_API_KEY);

        try{
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("api_key", apiKey);

            String response;
            try(InputStream is = conn.getInputStream()) {
                response = JsonResponseReader.readJsonFromResponse(is);
            }
            return response;

        } catch (Exception e) {
            log.warning("Exception while making API call: " + e.toString());
            throw new WmataApiException(e);
        }
    }
}
