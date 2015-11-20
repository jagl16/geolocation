package jg.robolibs.geolocation;

import java.util.Date;

/**
 * Created by jgarcia on 11/18/15.
 * <p>
 * Position class to hold lat and long from a location. (Platform independent)
 */
public class Position {

    public double latitude;
    public double longitude;
    public long timestamp;

    public Position(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        timestamp = new Date().getTime();
    }

    public Position() {
        timestamp = new Date().getTime();
    }
}
