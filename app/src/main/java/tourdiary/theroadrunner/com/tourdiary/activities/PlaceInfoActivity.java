package tourdiary.theroadrunner.com.tourdiary.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import tourdiary.theroadrunner.com.tourdiary.R;

public class PlaceInfoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);

        Bundle extras = getIntent().getExtras();

        TextView place_name = (TextView) findViewById(R.id.place_name);
        TextView place_date = (TextView) findViewById(R.id.place_date);
        TextView place_latitude = (TextView) findViewById(R.id.place_latitude);
        TextView place_longitude = (TextView) findViewById(R.id.place_longitude);

        place_name.setText(extras.getString("PLACENAME"));
        place_date.setText(extras.getString("PLACEDATE"));
        place_latitude.setText(extras.getString("PLACELAT"));
        place_longitude.setText(extras.getString("PLACELONG"));
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
