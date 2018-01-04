package metrotracker.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import metrotracker.JsonSerializable;
import metrotracker.model.BusPosition;

public class BusPositions implements JsonSerializable {

    @Expose
    private BusPosition[] BusPositions;

    public BusPositions() {}

    @Override
    public String toJson() {
        return new Gson().toJson(this.BusPositions);
    }
}
