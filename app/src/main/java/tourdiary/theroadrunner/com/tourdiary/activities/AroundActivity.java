package tourdiary.theroadrunner.com.tourdiary.activities;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import tourdiary.theroadrunner.com.tourdiary.R;

/**
 * Created by Dobromir on 11.10.2014 г..
 */
public class AroundActivity extends Activity {

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.around_activity);

        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        locationManager = null;
        locationListener = null;
    }

    class MyLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {

            //TODO implement logic to find distance between two points
            double longt = location.getLongitude();
            double lat = location.getLatitude();

            Log.i("POSITION", ""+ String.valueOf(longt) + "; " + String.valueOf(lat) + ";");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
