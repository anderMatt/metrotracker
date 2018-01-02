package metrotracker.model;

import metrotracker.model.BusPosition;

public class BusPositions {
    private BusPosition[] busPositions;

    public BusPositions() {}

    public void setBusPositions(BusPosition[] busPositions) {
        this.busPositions = busPositions;
    }

    public BusPosition[] getBusPositions() {
        return busPositions;
    }

}
