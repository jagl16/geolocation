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
