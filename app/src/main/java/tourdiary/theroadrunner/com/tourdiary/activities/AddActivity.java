package tourdiary.theroadrunner.com.tourdiary.activities;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import tourdiary.theroadrunner.com.tourdiary.R;
import tourdiary.theroadrunner.com.tourdiary.activities.dao.Place;
import tourdiary.theroadrunner.com.tourdiary.activities.dao.PlacesDataSource;


public class AddActivity extends ListActivity implements OnItemClickListener,View.OnClickListener{
    private PlacesDataSource datasource;
    private LocationManager locationManager;
    private LocationListener listener;
    private String latitude;
    private String longitude;
    private Context mContext = this;

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
        //Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //latitude = String.valueOf(location.getLatitude());
        //longitude = String.valueOf(location.getLongitude());
        listener = new myLocationListener();

        ListView list = (ListView) findViewById(android.R.id.list);
        list.setOnItemClickListener(this);
        list.setLongClickable(true);
        list.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                builder.setMessage(R.string.dialog_message)
                        .setTitle(R.string.dialog_title);

                AlertDialog dialog = builder.create();

                return true;
            }
        });
    }

    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Place> adapter = (ArrayAdapter<Place>) getListAdapter();
        Place place = null;
        switch (view.getId()) {
            case R.id.add:
                EditText editText = (EditText) findViewById(R.id.add_to_SQLite);
                String placeName = editText.getText().toString();
                String date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                boolean isPlaceNameValid = checkIfPlaceNameIsValid(placeName);

                // save the new place to the database
                if(latitude == null || longitude == null){
                    Toast.makeText(this, "Latitude or longitude cannot be null!", Toast.LENGTH_SHORT).show();
                }else {
                    if(isPlaceNameValid) {
                        place = datasource.createPlace(placeName, latitude, longitude, date);
                        adapter.add(place);

                        String toastMessage = placeName + " was successfully added to your list!";
                        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Place name should be between 1 and 50 symbols!", Toast.LENGTH_SHORT).show();
                    }
                }
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
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Intent intent = new Intent(AddActivity.this,PlaceInfoActivity.class);
        ArrayAdapter<Place> adapter = (ArrayAdapter<Place>) getListAdapter();
        Place temp =(Place) adapter.getItem(position);
        intent.putExtra("PLACENAME", temp.getName());
        intent.putExtra("PLACEDATE", temp.getDate());
        intent.putExtra("PLACELAT", temp.getLatitude());
        intent.putExtra("PLACELONG", temp.getLongitude());
        startActivity(intent);
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

    private boolean checkIfPlaceNameIsValid(String placeName){
        if(placeName != null && placeName.length() > 0 && placeName.length() <= 100){
                return true;
        }else{
            return false;
        }
    }
}
