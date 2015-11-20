package jg.robolibs.geolocation;

/**
 * Created by jgarcia on 11/20/15.
 */
public interface IGeolocator {
    int getDesiredAccuracy();

    void setDesiredAccuracy(int accuracy);

    boolean isGeolocationAvailable();

    boolean isGeolocationEnabled();

    void startListening(long minTime, float minDistance);

    void stopListening();

    Position getLastPosition();

    void addPositionChangeListener(LocationChangeListener listener);

    void addPositionErrorListener(PositionErrorListener listener);

    void removePositionErrorListener(PositionErrorListener listener);

    void removePositionChangeListener(LocationChangeListener listener);

    void removeAllPositionChangeListeners();

    void removeAllPositionErrorListeners();

    void notifyPositionChange(Position position);

    // Events
    interface PositionErrorListener {
        void onError();
    }

    interface LocationChangeListener {
        void onLocationChanged(Position position);
    }
}