package metrotracker.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import metrotracker.JsonSerializable;

public class BusRoutes implements JsonSerializable{

    @Expose
    private BusRoute[] Routes;

    public BusRoutes() {}

    @Override
    public String toJson() {
        return new Gson().toJson(this.Routes);
    }
}
