package tourdiary.theroadrunner.com.tourdiary.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import tourdiary.theroadrunner.com.tourdiary.R;
import tourdiary.theroadrunner.com.tourdiary.activities.dao.PlacesDataSource;
import tourdiary.theroadrunner.com.tourdiary.activities.dao.Place;


public class AddActivity extends ListActivity {
    private PlacesDataSource datasource;

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

                // save the new comment to the database
                place = datasource.createPlace(placeName);
                adapter.add(place);

                String toastMessage = placeName + " was successfully added to database!";
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
}
