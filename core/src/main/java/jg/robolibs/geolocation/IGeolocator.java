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