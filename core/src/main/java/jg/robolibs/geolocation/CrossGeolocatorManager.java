package jg.robolibs.geolocation;

/**
 * Created by jgarcia on 11/20/15.
 * Cross platform Geo locator provider.
 *
 * In order to use correctly the plugin this class needs to be configured when the application starts.
 */
public class CrossGeolocatorManager {

    private IGeolocator geolocator;

    private static CrossGeolocatorManager instance;

    boolean configured = false;

    public synchronized static CrossGeolocatorManager getInstance() {
        if (instance == null)
            instance = new CrossGeolocatorManager();
        return instance;
    }

    /**
     * OS implementation locator to configure.
     * @param locator
     */
    public static synchronized CrossGeolocatorManager configure(IGeolocator locator) {
        if (locator == null)
            throw new IllegalArgumentException("IGeolocator can't be null");
        if(instance == null)
            instance = new CrossGeolocatorManager();
        instance.configured = true;
        instance.geolocator = locator;
        return instance;
    }

    public IGeolocator getGeolocator() {
        return geolocator;
    }
}
