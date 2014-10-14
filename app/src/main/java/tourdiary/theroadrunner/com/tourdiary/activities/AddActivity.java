package tourdiary.theroadrunner.com.tourdiary.activities;

import android.app.ListActivity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import tourdiary.theroadrunner.com.tourdiary.R;
import tourdiary.theroadrunner.com.tourdiary.activities.dao.Place;
import tourdiary.theroadrunner.com.tourdiary.activities.dao.PlacesDataSource;


public class AddActivity extends ListActivity {
    private PlacesDataSource datasource;
    private LocationManager locationManager;
    private LocationListener listener;
    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        datasource = new PlacesDataSource(this);
        datasource.open();

        List<Place> values = datasource.getAllPlaces();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Place> adapter = new ArrayAdapter<Place>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
       // Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
       // latitude = String.valueOf(location.getLatitude());
       // longitude = String.valueOf(location.getLongitude());
        listener = new myLocationListener();

    }

    // Will be called via the onClick attribute
    // of the buttons
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Place> adapter = (ArrayAdapter<Place>) getListAdapter();
        Place place = null;
        switch (view.getId()) {
            case R.id.add:
                EditText editText = (EditText) findViewById(R.id.add_to_SQLite);
                String placeName = editText.getText().toString();
                String date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                // save the new place to the database
                place = datasource.createPlace(placeName,latitude,longitude,date);
                adapter.add(place);

                String toastMessage = placeName +" date: "+ date;//+ " was successfully added to database!";
                Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    place = (Place) getListAdapter().getItem(0);
                    datasource.deletePlace(place);
                    adapter.remove(place);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.add_activity, menu);
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

    class myLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            latitude = String.valueOf(location.getLatitude());
            longitude = String.valueOf(location.getLongitude());
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
