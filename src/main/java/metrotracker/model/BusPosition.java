package metrotracker.model;

public class BusPosition {
    private String dateTime;
    private int Deviation;
    private String directionNum;
    private String directionText;
    private long lat;
    private long lon;
    private String tripEndTime;
    private String tripHeadSign;
    private String tripStartTime;
    private String vehicleID;

    public BusPosition() {}

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setDeviation(int deviation) {
        Deviation = deviation;
    }

    public void setDirectionNum(String directionNum) {
        this.directionNum = directionNum;
    }

    public void setDirectionText(String directionText) {
        this.directionText = directionText;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }

    public void setTripEndTime(String tripEndTime) {
        this.tripEndTime = tripEndTime;
    }

    public void setTripHeadSign(String tripHeadSign) {
        this.tripHeadSign = tripHeadSign;
    }

    public void setTripStartTime(String tripStartTime) {
        this.tripStartTime = tripStartTime;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getDeviation() {
        return Deviation;
    }

    public String getDirectionNum() {
        return directionNum;
    }

    public String getDirectionText() {
        return directionText;
    }

    public long getLat() {
        return lat;
    }

    public long getLon() {
        return lon;
    }

    public String getTripEndTime() {
        return tripEndTime;
    }

    public String getTripHeadSign() {
        return tripHeadSign;
    }

    public String getTripStartTime() {
        return tripStartTime;
    }

    public String getVehicleID() {
        return vehicleID;
    }
}
