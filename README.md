# robogeolocation
Robo VM Geolocation Plugin


**IMPORTANT**
Android:
You must request android.permission.ACCESS_COARSE_LOCATION & android.permission.ACCESS_FINE_LOCATION permission

iOS:
In iOS 8 you now have to call either RequestWhenInUseAuthorization or RequestAlwaysAuthorization on the location manager. Additionally you need to add either the concisely named NSLocationWhenInUseUsageDescription or NSLocationAlwaysUsageDescription to your Info.plist.


Getting Started:

<pre>
    //When the application starts call
    //iOS
    CrossGeolocatorManager.configure(new IOSGeolocator());
    //Android
    CrossGeolocatorManager.configure(new DroidGeolocator());
</pre>

<pre>
    //Get platform independent Geolocator manager object
    IGeolocator locator = CrossGeolocator.getInstance();

    //Returns the last position the Location manager had:
    Position locator = locator.getLastPosition();

    //Starts listening for locations

    LocationChangeListener locListener = new LocationChangeListener(){
         void onLocationChanged(Position position){
            //Do something with the last position object.
         }
    };

    locator.addPositionChangeListener(locListener);
    locator.startListening(1000, 0, true);

    //clean up
    locator.removePositionChangedListener(locListener);
    //or locator.removeAllPositionChangeListeners(locListener);

    System.out.println("Position Status: "+position.timestamp);
    System.out.println("Position Latitude: "+position.latitude);
    System.out.println("Position Longitude: "+ position.longitude);
</pre>



