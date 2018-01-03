package metrotracker.utli;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import metrotracker.model.BusPositions;

public class BusPositionsMapper {
    public static BusPositions fromJson(String jsonResponse) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        return gson.fromJson(jsonResponse, BusPositions.class);
    }
}
