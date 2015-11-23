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

import java.util.ArrayList;

/**
 * Created by jgarcia on 11/18/15.
 * Base Geolocator class, manages the references to the listeners.
 */
public abstract class BaseGeolocator implements IGeolocator {

    ArrayList<LocationChangeListener> locationChangeListeners = new ArrayList<>();
    ArrayList<PositionErrorListener> positionErrorListeners = new ArrayList<>();
    int accuracy;

    @Override
    public int getDesiredAccuracy() {
        return accuracy;
    }

    @Override
    public void setDesiredAccuracy(int acc) {
        accuracy = acc;
    }

    @Override
    public void addPositionChangeListener(LocationChangeListener listener) {
        locationChangeListeners.add(listener);
    }

    @Override
    public void addPositionErrorListener(PositionErrorListener listener) {
        positionErrorListeners.add(listener);
    }

    @Override
    public void removePositionErrorListener(PositionErrorListener listener) {
        positionErrorListeners.remove(listener);
    }

    @Override
    public void removePositionChangeListener(LocationChangeListener listener) {
        locationChangeListeners.remove(listener);
    }

    @Override
    public void removeAllPositionChangeListeners() {
        locationChangeListeners.clear();
    }

    @Override
    public void removeAllPositionErrorListeners() {
        positionErrorListeners.clear();
    }

    @Override
    public void notifyPositionChange(final Position position) {
        for (LocationChangeListener listener : locationChangeListeners) {
            listener.onLocationChanged(position);
        }
    }
}
