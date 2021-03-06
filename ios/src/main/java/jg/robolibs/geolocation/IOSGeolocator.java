/*
 * Copyright (C) 2015 Jesus Garcia.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jg.robolibs.geolocation;

import org.robovm.apple.corelocation.CLAuthorizationStatus;
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
        adapter = new IOSDelegateAdapter(this);
        CLAuthorizationStatus status = CLLocationManager.getAuthorizationStatus();
        if(status == CLAuthorizationStatus.Denied || status == CLAuthorizationStatus.Restricted)
            throw new SecurityException("Authorization services are not enabled or are restricted");
        locationManager.requestAlwaysAuthorization();
        locationManager.setDelegate(adapter);
        if(!CLLocationManager.isLocationServicesEnabled()) {
            //don't do anything
            throw new IllegalArgumentException("CLLocationManager location services are disabled");
        }

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
        locationManager.startUpdatingLocation();
    }

    @Override
    public void stopListening() {
        locationManager.stopUpdatingLocation();
    }

    @Override
    public Position getLastPosition() {
        CLLocation location = locationManager.getLocation();
        if(location == null)
            return null;
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
