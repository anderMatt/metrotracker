package metrotracker.model;

import com.google.gson.annotations.Expose;

public class BusPosition {

    @Expose
    private String dateTime;

    @Expose
    private int Deviation;

    @Expose
    private String directionNum;

    @Expose
    private String directionText;

    @Expose
    private double lat;

    @Expose
    private double lon;

    @Expose
    private String tripEndTime;

    @Expose
    private String tripHeadsign;

    @Expose
    private String tripStartTime;

    @Expose
    private String vehicleID;

    public BusPosition() {}
}
