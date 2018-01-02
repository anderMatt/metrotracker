package metrotracker.utli;

import com.google.gson.Gson;
import metrotracker.model.BusPositions;

public class BusPositionsMapper {
    public static BusPositions fromJson(String jsonResponse) {
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, BusPositions.class);
    }
}
