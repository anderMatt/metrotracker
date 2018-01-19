package metrotracker.utli;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import metrotracker.model.BusRoutes;

public class BusRoutesMapper {
    public static BusRoutes fromJson(String jsonResponse) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        return gson.fromJson(jsonResponse, BusRoutes.class);
    }
}
