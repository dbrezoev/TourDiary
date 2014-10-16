package tourdiary.theroadrunner.com.tourdiary.activities;

/**
 * Created by Dobromir on 15.10.2014 г..
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import tourdiary.theroadrunner.com.tourdiary.R;
import tourdiary.theroadrunner.com.tourdiary.activities.dao.Tracker;

public class LocationTrackerActivity extends Activity {

    Button btnShowLocation;

    // tourdiary.theroadrunner.com.tourdiary.activities.dao.GPSTracker class
    Tracker gps;

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

                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }
                else{

                    gps.showSettingsAlert();
                }

            }
        });
    }

}