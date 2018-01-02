package metrotracker.api;

import metrotracker.WmataApiException;
import metrotracker.utli.JsonResponseReader;

import java.util.logging.Logger;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import metrotracker.AppProperties;


public class WmataApi {
    private static final Logger log = Logger.getLogger(WmataApi.class.getName());

    public WmataApi() { }

    public static String getBusPositions(String routeId) throws WmataApiException {
        String endpointUrl = WmataApiEndpoints.BUS_POSITIONS + "?routeId=" + routeId;
        return makeApiCall(endpointUrl);
    }

    private static String makeApiCall(String endpoint) throws WmataApiException  {
        String apiKey = AppProperties.getConfiguration(AppProperties.Configuration.WMATA_API_KEY);
        if("".equals(apiKey)) {
            log.warning("No API key was retrieved from configurations!");
        }
        log.info("Making API call to endpoint " + endpoint);
        log.info("Using API key: " + apiKey);

        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("api_key", apiKey);

            String response;
            //TODO: check response code for better err. message, eg. key expired.
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
