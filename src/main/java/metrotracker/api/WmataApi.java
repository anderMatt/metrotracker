package metrotracker.api;

import metrotracker.WmataApiException;
import metrotracker.utli.JsonResponseReader;

import java.util.logging.Logger;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class WmataApi {
    private static final Logger log = Logger.getLogger(WmataApi.class.getName());
    private String apiKey;

    public WmataApi(String apiKey) {
        if(apiKey == null || "".equals(apiKey)) {
            throw new RuntimeException("A WMATA Api key must be provided.");
        }
        this.apiKey = apiKey;
    }

    public String getBusPositions(String routeId) throws WmataApiException {
        String endpointUrl = WmataApiEndpoints.BUS_POSITIONS + "?routeId=" + routeId;
        return makeApiCall(endpointUrl);
    }

    private String makeApiCall(String endpoint) throws WmataApiException  {
        log.info("Making API call to endpoint " + endpoint);

        try{
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("api_key", this.apiKey);

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
