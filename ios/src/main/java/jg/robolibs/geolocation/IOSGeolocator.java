package jg.robolibs.geolocation;

import org.robovm.apple.corelocation.CLLocation;
import org.robovm.apple.corelocation.CLLocationManager;
import org.robovm.apple.corelocation.CLLocationManagerDelegateAdapter;
import org.robovm.apple.foundation.NSArray;

import java.lang.ref.WeakReference;

/**
 * Created by jgarcia on 11/20/15.
 * IOS location manager handler
 */
public class IOSGeolocator extends BaseGeolocator {

    CLLocationManager locationManager;
    IOSDelegateAdapter adapter;

    public IOSGeolocator() {
        locationManager = new CLLocationManager();
        locationManager.requestWhenInUseAuthorization();
        locationManager.setDelegate(adapter);
    }

    @Override
    public boolean isGeolocationAvailable() {
        return false;
    }

    @Override
    public boolean isGeolocationEnabled() {
        return false;
    }

    @Override
    public void startListening(long minTime, float minDistance) {
        locationManager.startMonitoringSignificantLocationChanges();
    }

    @Override
    public void stopListening() {
        locationManager.stopUpdatingLocation();
    }

    @Override
    public Position getLastPosition() {
        CLLocation location = locationManager.getLocation();
        return new Position(location.getCoordinate().getLatitude(), location.getCoordinate().getLongitude());
    }

    protected static final class IOSDelegateAdapter extends CLLocationManagerDelegateAdapter {
        WeakReference<IOSGeolocator> geolocator;

        protected IOSDelegateAdapter(IOSGeolocator geolocator) {
            this.geolocator = new WeakReference<>(geolocator);
        }

        @Override
        public void didUpdateLocations(CLLocationManager manager, NSArray<CLLocation> locations) {
            IGeolocator locator = geolocator.get();
            CLLocation cLocation = locations.get(0);
            if (cLocation != null) {
                Position position = new Position(cLocation.getCoordinate().getLatitude(), cLocation.getCoordinate().getLongitude());
                if (locator != null)
                    locator.notifyPositionChange(position);
            }
        }
    }
}
