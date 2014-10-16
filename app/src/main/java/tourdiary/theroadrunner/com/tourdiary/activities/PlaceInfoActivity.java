package tourdiary.theroadrunner.com.tourdiary.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import tourdiary.theroadrunner.com.tourdiary.R;

public class PlaceInfoActivity extends ActionBarActivity {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);

        Bundle extras = getIntent().getExtras();

        TextView place_name = (TextView) findViewById(R.id.place_name);
        TextView place_date = (TextView) findViewById(R.id.place_date);

        String name = extras.getString("PLACENAME");
        String date = extras.getString("PLACEDATE");
        String latitudeAsString = extras.getString("PLACELAT");
        String longitudeAsString = extras.getString("PLACELONG");

        place_name.setText(name);
        place_date.setText(date);

        double latitude = Double.parseDouble(latitudeAsString);
        double longitude = Double.parseDouble(longitudeAsString);

        LatLng currentLocation = new LatLng(latitude, longitude);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.place_map))
                .getMap();
        Marker currentLocationMarker = map.addMarker(new MarkerOptions().position(currentLocation)
                .title(name));

        // Move the camera instantly to current location with a zoom of 10.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.place_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
