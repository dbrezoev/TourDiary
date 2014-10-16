package tourdiary.theroadrunner.com.tourdiary.activities;

/**
 * Created by Dobromir on 15.10.2014 г..
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import tourdiary.theroadrunner.com.tourdiary.R;
import tourdiary.theroadrunner.com.tourdiary.activities.dao.Tracker;

public class LocationTrackerActivity extends Activity {

    Button btnShowLocation;

    // tourdiary.theroadrunner.com.tourdiary.activities.dao.GPSTracker class
    Tracker gps;
    private GoogleMap map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.around_activity);

        btnShowLocation = (Button) findViewById(R.id.my_button);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                gps = new Tracker(LocationTrackerActivity.this);

                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    LatLng currentLocation = new LatLng(latitude, longitude);
                    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                            .getMap();
                    Marker currentLocationMarker = map.addMarker(new MarkerOptions().position(currentLocation)
                            .title("You are here"));

                    // Move the camera instantly to current location with a zoom of 10.
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10));

                    // Zoom in, animating the camera.
                    map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                }
                else{

                    gps.showSettingsAlert();
                }

            }
        });
    }

}