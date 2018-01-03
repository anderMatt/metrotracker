package metrotracker.api;

import metrotracker.WmataApiException;
import metrotracker.model.BusPositions;
import metrotracker.utli.BusPositionsMapper;
import metrotracker.utli.ApiResponseUtil;

import java.io.IOException;
import java.util.logging.Logger;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import metrotracker.AppProperties;


public class WmataApi {
    private static final Logger log = Logger.getLogger(WmataApi.class.getName());

    public WmataApi() { }

    public static BusPositions getBusPositions(String routeId) throws WmataApiException {
        String endpointUrl = WmataApiEndpoints.BUS_POSITIONS + "?routeId=" + routeId;
        String apiResponseBody = makeApiCall(endpointUrl);
        return BusPositionsMapper.fromJson(apiResponseBody);
    }

    private static String makeApiCall(String endpoint) throws WmataApiException  {
        String apiKey = AppProperties.getConfiguration(AppProperties.Configuration.WMATA_API_KEY);
        if("".equals(apiKey)) {
            log.warning("No API key was retrieved from configurations!");
        }
        log.info("Making API call to endpoint " + endpoint);
        log.info("Using API key: " + apiKey);

        HttpURLConnection conn = null;
        String responseBody = "";

        try {
            URL url = new URL(endpoint);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("api_key", apiKey);

            int responseCode = conn.getResponseCode();

            if(ApiResponseUtil.isErrorResponseCode(responseCode)){
                responseBody = readErrorResponseBody(conn);
            }
            else {
                responseBody = readSuccessfulResponseBody(conn);
            }

            return responseBody;

        } catch (IOException e) {
            log.warning("Exception while making WMATA API call: " + e.toString());
            throw new WmataApiException(e);
        }
        finally {
            if(conn != null) {
                conn.disconnect();
            }
        }
    }

    private static String readSuccessfulResponseBody(HttpURLConnection conn) throws IOException {
        String responseBody = "";

        try(InputStream is = conn.getInputStream()) {
           responseBody = ApiResponseUtil.readJsonFromResponseStream(is);
        }
        return responseBody;
    }

    private static String readErrorResponseBody(HttpURLConnection conn) throws IOException {
        String responseBody = "";

        try(InputStream es = conn.getErrorStream()) {
            responseBody = ApiResponseUtil.readJsonFromResponseStream(es);
        }

        //Log hints.
        int responseCode = conn.getResponseCode();
        switch(responseCode) {
            case 401:  //TODO: other codes
                log.warning("Make sure the API key " + AppProperties.getConfiguration(AppProperties.Configuration.WMATA_API_KEY) + " is valid.");
                break;
            default: break;
        }
        return responseBody;
    }
}
