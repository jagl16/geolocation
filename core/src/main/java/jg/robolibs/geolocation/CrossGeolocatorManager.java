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
